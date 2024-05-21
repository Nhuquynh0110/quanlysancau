package model;

public class ItemModel {
    private int idItem;
    private String nameItem;
    private String typeItem;
    private String unitItem;
    private float priceItem;

    public ItemModel() {
        super();
    }

    public ItemModel(String nameitem, String typeitem, String unititem, float price){
        this.nameItem=nameitem;//dùng cho nút lưu
        this.typeItem=typeitem;
        this.unitItem=unititem;
        this.priceItem=price;
    }
    public ItemModel(int idItem,String nameItem,String typeItem,String unitItem,float priceItem){
        this.idItem=idItem;
        this.nameItem=nameItem;
        this.typeItem=typeItem;
        this.unitItem=unitItem;
        this.priceItem=priceItem;
    }

    public int getIdItem() {
        return idItem;
    }

    public String getNameItem() {
        return nameItem;
    }

    public String getTypeItem() {
        return typeItem;
    }

    public String getUnitItem() {
        return unitItem;
    }

    public float getPriceItem() {
        return priceItem;
    }

    
    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public void setTypeItem(String typeItem) {
        this.typeItem = typeItem;
    }

    public void setUnitItem(String unitItem) {
        this.unitItem = unitItem;
    }

    public void setPriceItem(int priceItem) {
        this.priceItem = priceItem;
    }

    @Override
    public String toString() {
        return "Item{" + "idItem=" + idItem + ", nameItem=" + nameItem + ", typeItem=" + typeItem + ", unitItem=" + unitItem + ", priceItem=" + priceItem + '}';
    }
    
    
    
}
   
    

