package pe.edu.upeu.www.example_sqlite01;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Lista_Activity extends ActionBarActivity {

    BD_SQLITE cx;
    ListView ListUser1;
    ArrayAdapter <String> adapter;
    private String[] list = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_);
        cx = new BD_SQLITE(this,"usuariobd",null,1);
        ListUser1 = (ListView) findViewById(R.id.ListUser);
        ReporteUsuario();

        Toast.makeText(getApplicationContext(), " " + list[1], Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void ReporteUsuario()
    {
        //String[] list;
        int i=0;
        Cursor c = cx.getReadableDatabase().rawQuery(" SELECT campo1, campo2 FROM usuario ",null);

        if(c.moveToFirst())
        {
           do{
               list[i] = c.getString(0).toString()+" "+c.getString(1).toString();
               i++;
           }while(c.moveToNext());

        }
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        //ListUser1.setAdapter(adapter);

    }

}
