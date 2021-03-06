package pe.edu.upeu.www.example_sqlite01;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    BD_SQLITE cx;
    EditText txt1, txt2, txt3;
    String item="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cx = new BD_SQLITE(this,"usuariobd",null,1);// SE CREA MI BD Y LA TABLA USUARIO
        txt1 = (EditText) findViewById(R.id.TxtUs);
        txt2 = (EditText) findViewById(R.id.TxtEmail);
        txt3 = (EditText) findViewById(R.id.TxtPw);
        try{
            item = (String) getIntent().getExtras().getSerializable("item");
            if(!item.equals("")){
                CargarUpdateUsuario();
            }
        }catch(Exception e){

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent opc1 = new Intent(this, Lista_Activity.class);
            startActivity(opc1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void limpiar_text()
    {
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
    }
    public void insertar_user(View v)
    {
        if(item.equals("")) {
            if (txt1.getText().length() > 0 && txt2.getText().length() > 0 && txt3.getText().length() > 0) {

                cx.getWritableDatabase().execSQL("INSERT INTO usuario( campo1 , campo2 , campo3 ) " +
                        " VALUES ('" + txt1.getText().toString() + "','" + txt2.getText().toString() + "','" + txt3.getText().toString() + "')");
                cx.close();
                Toast.makeText(this.getApplicationContext(), "se inserto corectamente", Toast.LENGTH_SHORT).show();
                limpiar_text();
            }
        }else{
            cx.getWritableDatabase().execSQL("UPDATE usuario " +
                    " SET campo1 = '" + txt1.getText().toString() + "', campo2 = '" + txt2.getText().toString() + "', campo3 = '" + txt3.getText().toString() + "' " +
                    " WHERE id_usuario="+Integer.parseInt(item));
            cx.close();
            Toast.makeText(this.getApplicationContext(), "se actualizó corectamente", Toast.LENGTH_SHORT).show();
            limpiar_text();
            item="";
        }
    }
    public void CargarUpdateUsuario()
    {

        Cursor c = cx.getReadableDatabase().rawQuery(" SELECT id_usuario , campo1, campo2 , campo3 FROM usuario WHERE id_usuario="+Integer.parseInt(item),null);

        if(c.moveToFirst())
        {
            do{
                txt1.setText(c.getString(1).toString());
                txt2.setText(c.getString(2).toString());
                txt3.setText("");
            }while(c.moveToNext());

        }

        /*txt1.setText(cx.);
        txt2.setText("");
        txt3.setText("");*/
    }
}
