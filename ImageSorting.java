import java.util.*;

/**
 * @author Salman
 */
public class ImageSorting {

    public static class Photo {
        String imageName;
        String place;
        String timeStamp;

        public String getPlace() {
            return place;
        }

        public void setImageName(String imageName) {
            this.imageName = imageName;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        Photo(String img, String loc, String time) {
            imageName = img;
            place =loc;
            timeStamp = time;
        }
    }

    public static void main(String[] args) {
        String str = "photo.jpg, Warsaw, 2013-09-05 14:08:15\n" +
                "john.png, London, 2015-06-20 15:13:22\n" +
                "myFriends.png, Warsaw, 2013-09-05 14:07:13\n" +
                "Eiffel.jpg, Paris, 2015-07-23 08:03:02\n" +
                "pisatower.jpg, Paris, 2015-07-22 23:59:59\n" +
                "BOB.jpg, London, 2015-08-05 00:02:03\n" +
                "notredame.png, Paris, 2015-09-01 12:00:00\n" +
                "me.jpg, Warsaw, 2013-09-06 15:40:22\n" +
                "a.png, Warsaw, 2016-02-13 13:33:50\n" +
                "b.jpg, Warsaw, 2016-01-02 15:12:22\n" +
                "c.jpg, Warsaw, 2016-01-02 14:34:30\n" +
                "d.jpg, Warsaw, 2016-01-02 15:15:01\n" +
                "e.png, Warsaw, 2016-01-02 09:49:09\n" +
                "f.png, Warsaw, 2016-01-02 10:55:32\n" +
                "g.jpg, Warsaw, 2016-02-29 22:13:11";


        String[] images = str.split("\\r?\\n");

        List<Photo> photoList = new ArrayList<>();

        for(int i=0; i<images.length; i++) {
            String[] splits = images[i].split(", ");

            photoList.add(new Photo(splits[0],splits[1],splits[2]));
        }

        photoList.sort(Comparator.comparing(Photo::getPlace).thenComparing(Photo::getTimeStamp));

        int index = -1;
        String firstPlace = photoList.get(0).getPlace();
        for(int i=0; i<photoList.size(); i++) {
            if(photoList.get(i).getPlace().equals(firstPlace)) {
                index ++;
            } else {
                firstPlace = photoList.get(i).getPlace();
                index = 0;
            }
            photoList.get(i).setImageName(photoList.get(i).getPlace() + (index+1));
        }

        List<Photo> modifiedList = new ArrayList<>();
        for(int i=0; i<images.length;i++) {
            String[] splits = images[i].split(", ");
            String timeStamp = splits[2];

            for(int j=0; j<photoList.size();j++) {
                String listPhotoTime = photoList.get(j).getTimeStamp();

                if(timeStamp.equals(listPhotoTime)) {
                    modifiedList.add(photoList.get(j));
                    break;
                }
            }
        }

        for(int i=0; i<modifiedList.size(); i++) {
            System.out.println(modifiedList.get(i).imageName);
        }
    }
}