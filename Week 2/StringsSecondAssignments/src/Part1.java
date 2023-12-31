public class Part1 {

    public int findStopCodon(String dna, int startIndex, String stopCodon){
        
        int currIdx = dna.indexOf(stopCodon, startIndex + 3);

        while( currIdx != -1 ){
            if((currIdx - startIndex) % 3 == 0){
                return currIdx;
            }else{
                currIdx = dna.indexOf(stopCodon, currIdx + 1);
            }
        }
        return dna.length();
    }

    public String findGene(String dna){
        int startIdx = dna.indexOf("ATG");
        if (startIdx == -1) {
            return "";
        }
        
        int TAAindex = findStopCodon(dna, startIdx, "TAA");
        int TAGindex = findStopCodon(dna, startIdx, "TAG");
        int TGAindex = findStopCodon(dna, startIdx, "TGA");

        int minIndex = 0;

        if (TAAindex == -1 || (TGAindex != -1 && TGAindex < TAAindex))
            minIndex = TGAindex;
        else
            minIndex = TAAindex;
        
        
        if (minIndex == -1 || (TAGindex != -1 && TAGindex < minIndex))
            minIndex = TAGindex;

        if ( minIndex == -1 ) return "";

        return dna.substring(startIdx, minIndex + 3);
    }

    public void printAllGene(String dna){
        while (true) {
            String currentGene = findGene(dna);
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println("Gene found: " + currentGene);
            dna = dna.substring(dna.indexOf(currentGene) + currentGene.length());
        }
    }
}
