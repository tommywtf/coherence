package bean;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Tommy on 4/4/2017.
 */
public class Order implements PortableObject {

    private String id;
    private String symbol;
    private double price;
    private int qty;
    private double value;
    private String userId;
    private String market;
    private Date tradeDateTime;
    private Date insertDateTime;

    public Order(){
    }

    public Order(String id, String symbol, double price, int qty, double value, String userId, String market, Date tradeDateTime, Date insertDateTime){
        this.id = id;
        this.symbol = symbol;
        this.price = price;
        this.value = value;
        this.qty = qty;
        this.userId = userId;
        this.market = market;
        this.tradeDateTime = tradeDateTime;
        this.insertDateTime = insertDateTime;
    }

    public String getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public double getValue() {
        return value;
    }

    public String getUserId() {
        return userId;
    }

    public String getMarket() {
        return market;
    }

    public Date getTradeDateTime() {
        return tradeDateTime;
    }

    public Date getInsertDateTime() {
        return insertDateTime;
    }

    public Key getKey(){
        return new Key(id, userId);
    }

    @Override
    public void readExternal(PofReader pofReader) throws IOException {
        id = pofReader.readString(0);
        symbol = pofReader.readString(1);
        price = pofReader.readDouble(2);
        qty = pofReader.readInt(3);
        value = pofReader.readDouble(4);
        userId = pofReader.readString(5);
        market = pofReader.readString(6);
        tradeDateTime = pofReader.readDate(7);
        insertDateTime = pofReader.readDate(8);
    }

    @Override
    public void writeExternal(PofWriter pofWriter) throws IOException {
        pofWriter.writeString(0, id);
        pofWriter.writeString(1, symbol);
        pofWriter.writeDouble(2, price);
        pofWriter.writeInt(3, qty);
        pofWriter.writeDouble(4, value);
        pofWriter.writeString(5, userId);
        pofWriter.writeString(6, market);
        pofWriter.writeDate(7, tradeDateTime);
        pofWriter.writeDate(8, insertDateTime);
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("id=").append(id);
        builder.append(" symbol=").append(symbol);
        builder.append(" price=").append(price);
        builder.append(" qty=").append(qty);
        builder.append(" value=").append(value);
        builder.append(" userId=").append(userId);
        return builder.toString();
    }
}
