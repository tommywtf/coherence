package bean;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

import java.io.IOException;

/**
 * Created by Tommy on 4/4/2017.
 */

public class Key implements PortableObject {
    private String id;
    private String userId;

    public Key(){
    }

    public Key(String id, String userId){
        this.id = id;
        this.userId = userId;
    }

    public String getId(){
        return id;
    }

    public String getUserId(){
        return userId;
    }

    @Override
    public void readExternal(PofReader pofReader) throws IOException {
        id = pofReader.readString(0);
        userId = pofReader.readString(1);
    }

    @Override
    public void writeExternal(PofWriter pofWriter) throws IOException {
        pofWriter.writeString(0, id);
        pofWriter.writeString(1, userId);
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("id=").append(id);
        builder.append(" userId=").append(userId);
        return builder.toString();
    }
}