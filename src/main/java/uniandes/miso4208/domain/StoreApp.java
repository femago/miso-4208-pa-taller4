package uniandes.miso4208.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class StoreApp {

    private final String id;
    private String name;
    private String description;
    private String ratingsCount;
    private String avgRating;
    private String changes;
    private String fiveStartCounts;
    private String fourStartCounts;
    private String comments;

    public StoreApp(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreApp storeApp = (StoreApp) o;
        return Objects.equal(id, storeApp.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .add("ratingsCount", ratingsCount)
                .add("avgRating", avgRating)
                .add("fiveStartCounts", fiveStartCounts)
                .add("fourStartCounts", fourStartCounts)
                .toString();
    }

    public static final List<String> headers(){
        return Arrays.asList(
                "App Id"
                ,"Nombre"
                ,"Descripción"
                ,"Calificaciones"
                ,"Calificación Promedio"
                ,"5 Estrellas"
                ,"4 Estrellas"
                ,"Comentarios");
    }

    public List<Object> asRecord(){
        ArrayList<Object> columns = new ArrayList<>();
        columns.add(id);
        columns.add(name);
        columns.add(description);
        columns.add(Integer.valueOf(ratingsCount.replaceAll(",","")));
        columns.add(Double.valueOf(avgRating.replaceAll(",",".")));
        columns.add(Integer.valueOf(fiveStartCounts.replaceAll(",","")));
        columns.add(Integer.valueOf(fourStartCounts.replaceAll(",","")));
        columns.add(comments);
        return columns;

    }
}
