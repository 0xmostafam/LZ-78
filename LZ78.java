import java.util.ArrayList;

public class LZ78 {
    private ArrayList<String> dictrionary = new ArrayList<String>();
    private ArrayList<Tag> tags = new ArrayList<Tag>();
    private String decompressedData = "";
    private String tempBuffer;
    private char readyBuffer;
    private int index;

    public void compress(String input) {
        dictrionary.add("");
        index = 0;
        boolean added = false;
        boolean lastLetter = false;
        while(!lastLetter){
            added = false;
            tempBuffer = "";
            while(!added) {
                readyBuffer = input.charAt(index);
                if(index == input.length() - 1) {
                    lastLetter = true;
                    Tag tag = new Tag(dictrionary.indexOf(tempBuffer) , readyBuffer);
                    tempBuffer = tempBuffer + readyBuffer;
                    tags.add(tag);
                    dictrionary.add(tempBuffer);
                    break;
                }
                if(dictrionary.contains(tempBuffer + readyBuffer)){
                    tempBuffer = tempBuffer + readyBuffer;
                    index++;
                } else {
                    Tag tag = new Tag(dictrionary.indexOf(tempBuffer) , readyBuffer);
                    tags.add(tag);
                    tempBuffer = tempBuffer + readyBuffer;
                    dictrionary.add(tempBuffer);
                    index++;
                    added = true;
                }
            }
        }
    }
    public String decompress(String input){
        dictrionary.clear();
        dictrionary.add("");
        String[] tokens = input.split("(?<! ) ");
        for(int i = 0 ; i < tokens.length;i++){
            char nextSymbol = tokens[i].charAt(0);
            int index = Integer.parseInt(tokens[i].substring(1));
            dictrionary.add(dictrionary.get(index)+ nextSymbol);
            decompressedData = decompressedData + dictrionary.get(index) + nextSymbol;
        }
        return decompressedData;
    }
    public void printList(){
        for (int i = 0 ; i < dictrionary.size() ; i++){
            System.out.println(i + " , " + dictrionary.get(i));
        }
    }

    public String returnTags(){
        String compressedData = "";
        for (int i = 0 ; i < tags.size() ; i++){
            compressedData = compressedData + tags.get(i).toString();
        }
        return compressedData;
    }
}
