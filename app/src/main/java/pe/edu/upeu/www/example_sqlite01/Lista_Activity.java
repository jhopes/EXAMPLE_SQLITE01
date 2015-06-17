package pe.edu.upeu.www.example_sqlite01;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Lista_Activity extends ActionBarActivity {

    BD_SQLITE cx;
    ListView ListUser1;
    ArrayAdapter <String> adapter;
    int cont=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_);
        cx = new BD_SQLITE(this,"usuariobd",null,1);
        ListUser1 = (ListView) findViewById(R.id.ListUser);
        //eporteUsuario();

        //Toast.makeText(getApplicationContext(), " " + list[1], Toast.LENGTH_SHORT).show();
        ListUser1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                if (cont==0) {
                    Intent opc1 = new Intent(Lista_Activity.this, MainActivity.class);
                    opc1.putExtra("item", item);
                    startActivity(opc1);
                    cont=0;
                }else if(cont==1){
                    DeleteUsuario(Integer.parseInt(item));
                    cont=0;
                }
                cont++;
            }
        });
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
    public void ReporteUsuario(View v)
    {

        int i=0;
        Cursor c = cx.getReadableDatabase().rawQuery(" SELECT id_usuario , campo1, campo2 , campo3 FROM usuario ",null);
        String[] list = new String[c.getCount()];
        if(c.moveToFirst())
        {
           do{
               list[i] = c.getString(0).toString();
               i++;
           }while(c.moveToNext());

        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        ListUser1.setAdapter(adapter);

    }
    public void DeleteUsuario(int item)
    {
        cx.getWritableDatabase().execSQL("DELETE FROM usuario WHERE id_usuario ="+item);
        cx.close();
    }


}
