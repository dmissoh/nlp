package com.kiolis.opennlp;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

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
            modelIS = this.getClass().getClassLoader().getResourceAsStream("opennlp/en-sent.bin");
            SentenceModel model = new SentenceModel(modelIS);


            SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
            String sentences[] = sentenceDetector.sentDetect("  First sentence. Second sentence. ");
            for(String sentence : sentences){
                System.out.println("Sentence: " + sentence);
            }

        } catch (IOException e) {
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
