package th.co.yuphasuk.wanchalerm.liveat500px.constant;

/**
 * Created by wanchalermyuphasuk on 2/10/2017 AD.
 */

public enum ArgumentEnum {
    PHOTO_ITEM_DAO("photo item argument");

    private String name;

    ArgumentEnum(String name) {
        this.name = name;
    }

   /* public String toString() {
        return super.toString();
    }*/

    @Override
    public String toString() {

        if(this.name != null){
            return this.name;
        }

        return "";
    }
}
