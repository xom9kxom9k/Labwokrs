import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneList {
    public ArrayList<Phone> ListData = new ArrayList();
    public Object[] to_String() {
        return ListData.toArray();
    }
    public List<Phone> streamFilter() {
        return ListData.stream().filter(i -> i.getVersion() > 10).collect(Collectors.toList());
    }
    public List<Phone>streamSearch(int versionInput, int yearReleasedInput, String nameInput){
        return ListData.stream().filter(i -> i.getVersion() == versionInput && i.getYearReleased()== yearReleasedInput && i.getName().contains(nameInput)).collect(Collectors.toList());
    }
}
