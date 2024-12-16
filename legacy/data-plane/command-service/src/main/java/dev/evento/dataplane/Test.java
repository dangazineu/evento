package dev.evento.dataplane;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Test {

    public static void main(String...args) throws Exception {
        System.out.println("Hello world!");

        InputStream input = new ByteArrayInputStream((
                "{\n" +
                "  \"operation\" : \"ADD\",\n" +
                "  \"firstParam\": {\n" +
                "    \"value\": 5654\n" +
                "  }" +
//                        ",\n" +
//                "  \"secondParam\": " +
//                        "{\n" +
//                "    \"value\": 5654\n" +
//                "  }\n"
//                        +
                "}"
                ).getBytes());
        DataInputStream din = new DataInputStream(input);

        Schema schema = new Schema.Parser().parse(
                "{\n" +
                        "  \"type\": \"record\",\n" +
                        "  \"name\": \"Operation\",\n" +
                        "  \"namespace\":\"dev.evento\",\n" +
                        "  \"fields\": [\n" +
                        "    {\n" +
                        "      \"name\": \"operation\",\n" +
                        "      \"type\": \"string\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"name\": \"firstParam\",\n" +
                        "      \"type\": {\n" +
                        "        \"type\": \"record\",\n" +
                        "        \"name\": \"OperationParam\",\n" +
//                        "        \"namespace\":\"dev.evento\",\n" +
                        "        \"fields\": [\n" +
                        "          {\n" +
                        "            \"name\": \"value\",\n" +
                        "            \"type\": \"int\"\n" +
                        "          }\n" +
                        "        ]\n" +
                        "      }\n" +
                        "    }" +
//                        ",\n" +
//                        "    {\n" +
//                        "      \"name\": \"secondParam\",\n" +
//                        "      \"type\": \"dev.evento.OperationParam\"\n" +
//                        "    }\n" +
                        "  ]\n" +
                        "}\n"
                );

        Decoder decoder = DecoderFactory.get().jsonDecoder(schema, din);
        DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>(schema);
        GenericRecord datum = reader.read(null, decoder);
        System.out.println("Operation is  " + datum.get("operation"));

    }
}
