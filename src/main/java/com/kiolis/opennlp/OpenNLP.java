package com.kiolis.opennlp;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: dimitri
 * Date: 6/16/13
 * Time: 1:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class OpenNLP {

    public static void main(String[] args){
        OpenNLP openNLP = new OpenNLP();
        openNLP.test();
    }

    private void test() {
        InputStream modelIS = null;
        try {
            modelIS = this.getClass().getClassLoader().getResourceAsStream("opennlp/en-parser-chunking.bin");
            ParserModel parserModel = new ParserModel(modelIS);
            Parser parser = ParserFactory.create(parserModel);
            String sentence = "The quick brown fox jumps over the lazy dog .";

            Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);
            for(Parse parse : topParses){
                parse.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (modelIS != null) {
                try {
                    modelIS.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
