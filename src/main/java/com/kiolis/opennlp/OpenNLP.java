package com.kiolis.opennlp;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.util.Sequence;

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

            modelIS = this.getClass().getClassLoader().getResourceAsStream("opennlp/fr-pos.bin");

            POSModel model = new POSModel(modelIS);

            POSTaggerME tagger = new POSTaggerME(model);
            String sentence = "The quick brown fox jumps over the lazy dog .";

            String sent[] = new String[]{"Most", "large", "cities", "in", "the", "US", "had",
                    "morning", "and", "afternoon", "newspapers", "."};
            String tags[] = tagger.tag(sent);

            for(String tag : tags){
                System.out.println("Tag: " + tag);
            }

            double probs[] = tagger.probs();

            Sequence topSequences[] = tagger.topKSequences(sent);

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
