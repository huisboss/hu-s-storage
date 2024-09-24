import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
interface ImdediaStandard{
    public String getMediaInfo();
}
interface IAudioStandard extends ImdediaStandard{
    String getAudioCodec();
}
interface IImageStrandard extends ImdediaStandard{
    String getImageCodec();
}
abstract class Media {
    private String fileName;
    private int id;
    private static int nextId =1;
    public Media(){
        this.id = nextId++;
    }
    public Media(String fileName){
        this.fileName=fileName;
        this.id=nextId++;
    }
    public String getFileName(){
        return fileName;
    }
    public int getId(){
        return id;
    }
    public abstract String getMediaInfo();
}
class Image extends Media implements IImageStrandard{
    private String imageCodec;
    public Image(String name,String imageCodec){
        super(name);
        this.imageCodec=imageCodec;
    }
    @Override
    public String getImageCodec(){
        return imageCodec;
    }
    @Override
    public String getMediaInfo(){
        return "\n\nImage Id: "+getId() + "\nImage name: "+getFileName()+"\nImage codec: "+getImageCodec();
    }
}
class Music extends Media implements IAudioStandard{
    private String audioCodec;
    public Music(String name , String audioCodec){
        super(name);
        this.audioCodec = audioCodec;
    }
    @Override
    public String getMediaInfo() {
        return "\n\nMusic ID: "+getId()+"\nMusic name: "+getFileName()+"\nAudio Codec: "+getAudioCodec();
    }

    @Override
    public String getAudioCodec() {
        return "Audio codec: "+ audioCodec;
    }
}
    class Vedio extends Media implements IImageStrandard,IAudioStandard{
        private String imageCodec;
        private String audioCodec;
        public Vedio(String name, String imageCodec, String audioCodec){
            super(name);
            this.imageCodec=imageCodec;
            this.audioCodec=audioCodec;
        }
        @Override
        public String getMediaInfo() {
            return "\n\nVideo ID: "+getId()+"\n\nVideo name: "+getFileName()+"\nImage Codec:: "+getImageCodec()+"\nAudio Codec: "+getAudioCodec();
        }

        @Override
        public String getAudioCodec() {
            return audioCodec;
        }

        @Override
        public String getImageCodec() {
            return imageCodec;
        }
    }
public class Main {
    public static void main(String[] args) {
        List<Media> allMedia = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("[Media Manager]");
        while (true) {
            System.out.print("\n1- Add Image\n" +
                    "2- Add Music\n" +
                    "3- Add Video\n" +
                    "4- Show images\n" +
                    "5- Show music\n" +
                    "6- Show videos\n" +
                    "7- Show images and videos\n" +
                    "8- Show music and videos\n" +
                    "9- Exit\n" +
                    "Enter option: ");
            int choose = scanner.nextInt();
            scanner.nextLine();
            switch (choose) {
                case 1:
                    System.out.print("Enter file name: ");
                    String Imagename = scanner.nextLine();
                    System.out.print("Enter image codec: ");
                    String imagecodec = scanner.nextLine();
                    allMedia.add(new Image(Imagename, imagecodec));
                    break;
                case 2:
                    System.out.print("Enter file name: ");
                    String Musicname = scanner.nextLine();
                    System.out.print("Enter audio codec: ");
                    String audiocodec = scanner.nextLine();
                    allMedia.add(new Music(Musicname, audiocodec));
                    break;

                case 3:
                    System.out.print("Enter file name: ");
                    String videoname = scanner.nextLine();
                    System.out.print("Enter image codec: ");
                    String videoimage = scanner.nextLine();
                    System.out.print("Enter audio codec: ");
                    String vedioaudiocodec = scanner.nextLine();
                    allMedia.add(new Vedio(videoname,videoimage,vedioaudiocodec));
                case 4:
                    for (Media media : allMedia) {
                        if (media instanceof Image) {
                            System.out.print(media.getMediaInfo());
                        }
                    }
                    break;
                case 5:
                    for (Media media : allMedia) {
                        if (media instanceof Music) {
                            System.out.println(media.getMediaInfo());
                        }
                    }
                    break;
                case 6:
                    for (Media media : allMedia) {
                        if (media instanceof Vedio) {
                            System.out.println(media.getMediaInfo());
                        }
                    }
                    break;
                case 7:
                    for (Media media : allMedia) {
                        if (media instanceof IImageStrandard) {
                            System.out.println(media.getMediaInfo());
                        }
                    }
                    break;
                case 8:
                    for (Media media : allMedia) {
                        if (media instanceof IAudioStandard) {
                            System.out.println(media.getMediaInfo());
                        }
                    }
                    break;
                case 9:
                    System.out.println("Shutting down...");
                    System.exit(0);
                    break;
            }
        }
    }
}