#!/bin/bash

function print_kafka_bootstrap_servers {
  if [ $# == 0 ]; then
    echo "Expected one argument with namespace"
    exit 1
  fi
  # TODO check exit code of minikube call before seding
  minikube service kafka-cluster-kafka-external-bootstrap --url -n "$1" | sed 's/http\?:\/\///'
}

function print_available_local_port {
  # TODO include most commonly used ports in the list to avoid blocking services from using them
  local ports_to_ignore=("$@")

  # making sure these variables won't leak
  local lower_port=0, upper_port=0, middle_port=0, port=0

  read -r lower_port upper_port < /proc/sys/net/ipv4/ip_local_port_range
  # this port is right in the middle of the range
  middle_port=$(($(($(("$upper_port"-"$lower_port"))/2))+"$lower_port"))
  # defines the starting point as a random port between the lowest port in range, and the middle of the range
  lower_port=$(shuf -i "$lower_port"-"$middle_port" -n 1)

  for ((port=lower_port; port<=upper_port; port++))
    do
      if (echo > /dev/tcp/0.0.0.0/$port) > /dev/null 2>&1; then
        continue
      else
        for ignore in "${ports_to_ignore[@]}"; do
          if [ "$port" == "$ignore" ]; then
            # $port is available, but should be ignored, so will continue scanning
            continue 2
          fi
        done
        # port is available and not in the ignore list, so print it and exit
        echo $port
        break
      fi
    done
}

# given a list of strings, prints the ones that match executable dataplane modules
function print_dataplane_executable_modules_from_list {
  # these are the modules that match the input
  local matches=( $(print_dataplane_modules_from_list "$@") )

  # these are all the executable modules
  local executable=( $(print_dataplane_executable_modules) )

  # and this is the intersection between both
  local executable_matches=($(comm -1 -2 <(printf '%s\n' "${matches[@]}" | LC_ALL=C sort) <(printf '%s\n' "${executable[@]}" | LC_ALL=C sort)))

  echo "${executable_matches[*]}"
}

function array_contains {
  local word=$1
  shift
  local words=("$@")
  # shellcheck disable=SC2076
  if  [[ " ${words[*]} " =~ " ${word} " ]]; then
    exit 0
  else
    exit 1
  fi
}

# prints all executable dataplane modules
function print_dataplane_executable_modules {
  print_dataplane_modules_from_list "entt" "mgr" "cmd"
}

# given a list of args, prints the ones that match dataplane modules
function print_dataplane_modules_from_list {
  local modules=()
  while [[ $# -gt 0 ]]; do
    local key="$1"
    case $key in
      cmd|command|command-service)
        modules+=("command-service")
        shift
        ;;

      entt|entity|entity-service)
        modules+=("entity-service")
        shift
        ;;

      mng|mgr|manager|process-manager)
        modules+=("process-manager")
        shift
        ;;
      *)
        shift
    esac
  done
  echo "${modules[*]}"
}

function print_namespace {
  while [[ $# -gt 0 ]]; do
    local key="$1"
    case $key in
      -n|--namespace)
        echo "$2"
        exit
        ;;
      *)
        shift
    esac
  done

  # if we got here, it's because no namespace option was given in the args
  # so get it from the git branch
  echo "evento-$(git branch | sed -n -e 's/^\* \(.*\)/\1/p')"
}

function print_cluster {
  while [[ $# -gt 0 ]]; do
    local key="$1"
    case $key in
      -c|--cluster)
        echo "$2"
        exit
        ;;
      *)
        shift
    esac
  done
}

function print_context {
  while [[ $# -gt 0 ]]; do
    local key="$1"
    case $key in
      -ctx|--context)
        echo "$2"
        exit
        ;;
      *)
        shift
    esac
  done
}

#function print_domain {
#  while [[ $# -gt 0 ]]; do
#    local key="$1"
#    case $key in
#      -d|--domain)
#        echo "$2"
#        exit
#        ;;
#      *)
#        shift
#    esac
#  done
#
#  # if we got here, it's because no domain name option was given in the args
#  # will use default instead
#  echo "domain"
#}
#
#function print_entity {
#  while [[ $# -gt 0 ]]; do
#    local key="$1"
#    case $key in
#      -e|--entity)
#        echo "$2"
#        exit
#        ;;
#      *)
#        shift
#    esac
#  done
#
#  # if we got here, it's because no entity name option was given in the args
#  # will use default instead
#  echo "entity"
#}

#function print_resource_prefix {
#  echo "$(print_domain "$@")-$(print_entity "$@")"
#}

function add_envvar_to_deployment {
  if [ $# != 3 ]; then
    echo "Expected three arguments: varname, varvalue and deployment module"
    exit 1
  fi
  local varname=$1
  local varvalue=$2
  local deployment_file="data-plane/$3/target/kubernetes/kubernetes-processed.yml"
  # this is a bit brittle, it assumes indentation won't change, and variable isn't already set
  sed -i "s/env:.*/env:\n        - name: $varname\n          value: $varvalue/" "$deployment_file"
}