package whatever.whatever;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class DataActivity extends AppCompatActivity {
    DatabaseHelper allDb;
    VisitedDatabaseHelper visitedDb;


    EditText editId,editName,editPriceRangeLow,editPriceRangeHigh,editStyle,editLocationX,editLocationY,editAddress,editLink,editImage;
    Button btnAddData;
    Button btnViewAll;
    Button btnUpdate;
    Button btnDelete;
    Button btnView;
    Button btnRand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        allDb = new DatabaseHelper(this);  //Set up VisitedDatabaseHelper
        visitedDb = new VisitedDatabaseHelper(this);

        editId = (EditText)findViewById(R.id.editText_ID);
        editName = (EditText)findViewById(R.id.editText_NAME);
        editPriceRangeLow = (EditText)findViewById(R.id.editText_priceRangeLow);
        editPriceRangeHigh = (EditText)findViewById(R.id.editText_priceRangeHigh);
        editStyle = (EditText)findViewById(R.id.editText_style);
        editLocationX = (EditText)findViewById(R.id.editText_location_x);
        editLocationY = (EditText)findViewById(R.id.editText_location_x);
        editAddress = (EditText)findViewById(R.id.editText_address);
        editLink = (EditText)findViewById(R.id.editText_link);
        editImage = (EditText)findViewById(R.id.editText_image);

        btnAddData = (Button)findViewById(R.id.button_add);
        btnViewAll = (Button)findViewById(R.id.button_viewAll);
        btnUpdate = (Button)findViewById(R.id.button_update);
        btnDelete = (Button)findViewById(R.id.button_delete);
        btnView = (Button)findViewById(R.id.button_view);
        btnRand = (Button)findViewById(R.id.button_rand);

        initDataBase();

        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addData(editId.getText().toString(), editName.getText().toString(),
                                editPriceRangeLow.getText().toString(), editPriceRangeHigh.getText().toString(),
                                editStyle.getText().toString(), editLocationX.getText().toString(),
                                editLocationY.getText().toString(), editAddress.getText().toString(),
                                editLink.getText().toString(), editImage.getText().toString(), true);
                    }
                }
        );

        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewAll(true);
                    }
                }
        );

        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateData(editId.getText().toString(), editName.getText().toString(),
                                editPriceRangeLow.getText().toString(), editPriceRangeHigh.getText().toString(),
                                editStyle.getText().toString(), editLocationX.getText().toString(),
                                editLocationY.getText().toString(), editAddress.getText().toString(),
                                editLink.getText().toString(), editImage.getText().toString());

                    }
                }
        );

        btnDelete.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        deleteData(editId.getText().toString(),false);   //Call deleteData() to delete
                    }
                }
        );

        btnView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        view(editId.getText().toString(),false);
                    }
                }

        );

        btnRand.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        randGenerate(true);
                    }
                }

        );


    }

    public void initDataBase(){
        //addData(String id, String name, String priceRange_low, String priceRange_high, String style, String location_x, String location_y, String address, String link, String image, boolean isVisited

        Log.e("Lencia: ", "initDataBase: Here1!" );

        addData("1","MeokBang Korean BBQ and Bar","201","400","Korean BBQ","22.280168","114.184282","6/F, Goldmark, 502 Hennessy Rd, Causeway Bay","http://www.openrice.com/zh/hongkong/restaurant/sr1.htm?chain_id=10003521&chain_name=%E7%82%91%E5%85%AB%E9%9F%93%E7%83%A4%20(%E6%89%80%E6%9C%89%E5%88%86%E5%BA%97)&region=0&s=3","1",true);
        addData("2","Wa Shou Yakiniku","201","400","Japanese BBQ","22.307012","114.172388","G/F, 23-27 Tak Wai Building, Cheung Lok St, Yau Ma Tei","http://www.openrice.com/zh/hongkong/restaurant/%E4%BD%90%E6%95%A6-%E5%92%8C%E5%8C%A0%E6%97%A5%E5%BC%8F%E7%87%92%E8%82%89%E5%BA%97/140194?tc=sr1","2",true);
        addData("3","Dabyida Restaurants and Bar","201","400","Italian","22.297233","114.173999","G/F, 5, Minden Avenue, Tsim Sha Tsui","http://www.openrice.com/zh/hongkong/restaurant/%E5%B0%96%E6%B2%99%E5%92%80-dabyida-restaurant-and-bar/127192?tc=sr1","3",true);
        addData("4","Sun Thai Restaurant","101","200","Thai","22.278403","114.178391","2/F, W Square, 314-324 Hennessy Rd, Wanchai","http://www.openrice.com/zh/hongkong/restaurant/%E7%81%A3%E4%BB%94-%E6%96%B0%E6%B3%B0%E6%9D%B1%E5%8D%97%E4%BA%9E%E9%A4%90%E5%BB%B3/189148?tc=sr1","4",true);
        addData("5","Tulsi Indian Restaurant","101","200","Indian","22.290745","114.200992","G/F, 7, Tsat Tsz Mui Rd., North Point","http://www.openrice.com/zh/hongkong/restaurant/%E5%8C%97%E8%A7%92-%E7%BE%85%E5%8B%92%E5%8D%B0%E5%BA%A6%E9%A4%90%E5%BB%B3/115381?tc=sr1","5",true);
        addData("6","Hong Kong Lao Shang Hai Restaurant","201","400","Shanghai","22.27881","114.176465","LG1, 238 Jaffe Rd, Hotel Novotel Hong Kong Century, Causeway Bay","http://www.openrice.com/zh/hongkong/restaurant/%E7%81%A3%E4%BB%94-%E8%80%81%E4%B8%8A%E6%B5%B7%E9%A3%AF%E5%BA%97/3100?tc=sr1","6",true);
        Log.e("Lencia: ", "initDataBase: Here2!" );
        addData("7","Mak Man Kee Noodle Shop","1","100","Hong Kong","22.304662","114.170451","G/F, 51 Parkes St, Jordan","http://www.openrice.com/en/hongkong/restaurant/jordan-mak-man-kee-noodle-shop/6016","7",true);
        addData("8","Picada","101","200","South American","22.282141","114.152386","Store A, GF, 16 Elgin St, Central","http://www.openrice.com/zh/hongkong/restaurant/%E4%B8%AD%E7%92%B0-picada/485483?tc=sr1","8",true);
        addData("9","Viet Thai","1","100","Vietnames Thai","22.286967","114.152118","G/F, Yue Thai Commercial Building, 128 Connaught Road Central,?Sheung Wan","http://www.openrice.com/en/hongkong/restaurant/sheung-wan-viet-thai/452569?tc=sr1","9",true);
        addData("10","Cheng Banzhang Taiwan Delicacy","1","100","Taiwanese","22.336884","114.14728","Flat 02, 1/F, Elite Industrial Centre, 883 Cheung Sha Wan Road, Lai Chi Kok","http://www.openrice.com/en/hongkong/restaurant/lai-chi-kok-cheng-banzhang-taiwan-delicacy/138997","10",true);
        addData("11","La Vache!","201","400","French","22.282407","114.152724","G/F, 48 Peel Street, Soho, Central","http://www.openrice.com/en/hongkong/restaurant/central-la-vache/155310?tc=sr1&con=phto","11",true);
        addData("12","Brunch Club and Supper","101","200","British","22.276657","114.180596","1/F, 13 Leighton Road, Causeway Bay","http://www.openrice.com/en/hongkong/restaurant/causeway-bay-brunch-club-supper/19138?tc=sr1","12",true);
        addData("13","K Lok Spicy Chicken Hotpot","201","400","Sichuan","22.302906","114.173573","Shop 4, G/F, Quality Tower, 29-31 Hillwood Road,?Tsim Sha Tsui","http://www.openrice.com/en/hongkong/restaurant/tsim-sha-tsui-k-lok-spicy-chicken-hot-pot/175318?tc=sr1","13",true);
        addData("14","Lucky Indonesian Restaurant","1","100","Indonesian","22.315801","114.223136","G/F, 46 Tung Ming Street,?Kwun Tong","http://www.openrice.com/en/hongkong/restaurant/sr1.htm?tc=sr1quick&s=1&region=0&inputstrwhat=&cuisine_id=2007&price=&inputstrwhere=","14",true);
        addData("15","Bigpack Caf?","201","400","German","22.316235","114.171573","3/F, 76A Fa Yuen Street,?Mong Kok","http://www.openrice.com/en/hongkong/restaurant/mong-kok-bigpack-cafe/47474?tc=sr1","15",true);
        Log.e("Lencia: ", "initDataBase: Here3!" );

    }

    public void deleteData(String idToDelete, boolean isVisited)
    {
        Integer deletedRows = 0;
        if(isVisited){
            deletedRows = visitedDb.deleteData(idToDelete);    //Delete data according to editId
        } else {
            deletedRows = allDb.deleteData(idToDelete);    //Delete data according to editId
        }

        if (deletedRows > 0)
            Toast.makeText(DataActivity.this,"Data deleted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(DataActivity.this,"Data not deleted", Toast.LENGTH_LONG).show();
    }

    public void updateData(String id, String name, String priceRange_low, String priceRange_high, String style, String location_x, String location_y, String address, String link, String image)
    {
        boolean isUpdated = allDb.updateData(id, name, priceRange_low, priceRange_high, style, location_x, location_y, address, link, image);
        if (isUpdated)
            Toast.makeText(DataActivity.this,"Data updated", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(DataActivity.this,"Data no updated", Toast.LENGTH_LONG).show();
    }

    public void viewAll(boolean isVisited)
    {
        Cursor res;
        if(isVisited){
            res = visitedDb.getAllData();                             //!!!
        } else {
            res = allDb.getAllData();                             //!!!
        }

        if (res.getCount() == 0) {
            // show message
            showMessage("ERROR","No Data Found");
            return;
        }

        StringBuffer buffer = new StringBuffer();                      //!!!
        while (res.moveToNext()) {
            //buffer.append("ID: " + res.getString(0) + '\n');
            buffer.append("ID: " + res.getString(0) + '\n');
            buffer.append("NAME: " + res.getString(1) + '\n');
            buffer.append("PRICERANGE_LOW: " + res.getString(2) + '\n');
            buffer.append("PRICERANGE_HIGH: " + res.getString(3) + '\n');
            buffer.append("STYLE: " + res.getString(4) + '\n');
            buffer.append("LOCATION_X: " + res.getString(5) + '\n');
            buffer.append("LOCATION_Y: " + res.getString(6) + '\n');
            buffer.append("ADDRESS: " + res.getString(7) + '\n');
            buffer.append("LINK: " + res.getString(8) + '\n');
            buffer.append("IMAGE: " + res.getString(9) + '\n');
            buffer.append("TIMESTAMP: " + res.getString(10) + '\n');
        }
        // show all data
        showMessage("Data", buffer.toString());
    }

    public void view(String idToView, boolean isVisited)
    {
        Cursor res;
        if(isVisited){
            res = visitedDb.getAllData();
        } else {
            res = allDb.getAllData();
        }

        if (res.getCount() == 0) {
            // show message
            showMessage("ERROR","No Data Found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        int isFound = 0;
        while (res.moveToNext()) {
            if (res.getString(0).equals(idToView))
            {
                //buffer.append("ID: " + res.getString(0) + '\n');
                buffer.append("ID: " + res.getString(0) + '\n');
                buffer.append("NAME: " + res.getString(1) + '\n');
                buffer.append("PRICERANGE_LOW: " + res.getString(2) + '\n');
                buffer.append("PRICERANGE_HIGH: " + res.getString(3) + '\n');
                buffer.append("STYLE: " + res.getString(4) + '\n');
                buffer.append("LOCATION_X: " + res.getString(5) + '\n');
                buffer.append("LOCATION_Y: " + res.getString(6) + '\n');
                buffer.append("ADDRESS: " + res.getString(7) + '\n');
                buffer.append("LINK: " + res.getString(8) + '\n');
                buffer.append("IMAGE: " + res.getString(9) + '\n');
                buffer.append("TIMESTAMP: " + res.getString(10) + '\n');

                isFound = 1;

            }
        }
        // show all data
        if (isFound == 0)
        {
            showMessage("ERROR","No Data Found");
            return;
        }
        showMessage("Data", buffer.toString());
    }

    public void addData(String id, String name, String priceRange_low, String priceRange_high, String style, String location_x, String location_y, String address, String link, String image, boolean isVisited)
    {
        boolean isUpdated = false;
        boolean isInserted = false;
        /*
        if(isVisited){
            isUpdated = visitedDb.updateData(id, name, priceRange_low, priceRange_high, style, location_x, location_y, address, link, image);
            if(!isUpdated){
                isInserted = visitedDb.insertData(id, name, priceRange_low, priceRange_high, style, location_x, location_y, address, link, image);
            } else {
                isInserted = true;
            }
        } else {
            isUpdated = allDb.updateData(id, name, priceRange_low, priceRange_high, style, location_x, location_y, address, link, image);
            if(!isUpdated){
                isInserted = allDb.insertData(id, name, priceRange_low, priceRange_high, style, location_x, location_y, address, link, image);
            } else {
                isInserted = true;
            }
        }
        */
        if(isVisited){
            isInserted = visitedDb.insertData(id, name, priceRange_low, priceRange_high, style, location_x, location_y, address, link, image);
        } else {
            isInserted = allDb.insertData(id, name, priceRange_low, priceRange_high, style, location_x, location_y, address, link, image);
        }

        if (isInserted)
            Toast.makeText(DataActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(DataActivity.this, "Data no inserted", Toast.LENGTH_LONG).show();

    }

    public void randGenerate(boolean isVisited){
        Cursor res;
        if(isVisited){
            res = visitedDb.getAllData();
        } else {
            res = allDb.getAllData();
        }

        int lengthOfList = res.getCount();   //store the length of the list

        if (lengthOfList == 0) {
            // show message
            showMessage("ERROR","No Data Found");
            return;
        }

        int randNum = rand(lengthOfList);   //get random number from th list


        StringBuffer buffer = new StringBuffer();
        int isFound = 0;
        while (res.moveToNext()) {
            if (res.getPosition() == randNum)
            {
                //buffer.append("ID: " + res.getString(0) + '\n');
                buffer.append("ID: " + res.getString(0) + '\n');
                buffer.append("NAME: " + res.getString(1) + '\n');
                buffer.append("PRICERANGE_LOW: " + res.getString(2) + '\n');
                buffer.append("PRICERANGE_HIGH: " + res.getString(3) + '\n');
                buffer.append("STYLE: " + res.getString(4) + '\n');
                buffer.append("LOCATION_X: " + res.getString(5) + '\n');
                buffer.append("LOCATION_Y: " + res.getString(6) + '\n');
                buffer.append("ADDRESS: " + res.getString(7) + '\n');
                buffer.append("LINK: " + res.getString(8) + '\n');
                buffer.append("IMAGE: " + res.getString(9) + '\n');
                buffer.append("TIMESTAMP: " + res.getString(10) + '\n');

                isFound = 1;

            }
        }
        // show all data
        if (isFound == 0)
        {
            showMessage("ERROR","No Data Found");
            return;
        }
        showMessage("Data", buffer.toString());
    }

    public int rand(int length){

        Random r = new Random();
        int tmp = r.nextInt(length);

        return tmp;
    }

    public void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
