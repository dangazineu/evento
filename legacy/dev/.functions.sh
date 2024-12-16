#!/bin/bash

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