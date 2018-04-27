package edu.illinois.cs.cs125.cookthree;

//import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
//import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //TextView text;
    //Typeface cookThreeTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textCookThree = (TextView) findViewById(R.id.cookthreetitle);
        Typeface tfCookThree = Typeface.createFromAsset(getAssets(), "fonts/ItalianBreakfast-Regular.ttf");
        textCookThree.setTypeface(tfCookThree);

        //Below are the three spinners used to choose your ingredients.
        Spinner spIngredient1 = (Spinner) findViewById(R.id.ingredient1);
        spIngredient1.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ingredients_meats, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spIngredient1.setAdapter(adapter);

        Spinner spIngredient2 = (Spinner) findViewById(R.id.ingredient2);
        spIngredient2.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.ingredients_grains, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spIngredient2.setAdapter(adapter2);

        Spinner spIngredient3 = (Spinner) findViewById(R.id.ingredient3);
        spIngredient3.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.ingredients_vegetables, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spIngredient3.setAdapter(adapter3);

        addListenerOnButton();
    }

    @Override
    public void onResume(){
        super.onResume();
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setVisibility(View.INVISIBLE);
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause(){
        super.onPause();
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setVisibility(View.GONE);
    }

    public void retrieveRecipes(View view, String ing1, String ing2, String ing3) {
        //Intent displayRecipes = new Intent(this, DisplayRecipeActivity.class);
        //startActivity(displayRecipes);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setVisibility(View.VISIBLE);
        String url = "https://www.allrecipes.com/search/results/?ingIncl=" + ing1 + "," +
                ing2 + "," + ing3 + "&sort=re";
        myWebView.loadUrl(url);
        /**
        Toast.makeText(MainActivity.this,
                ing1, Toast.LENGTH_LONG).show();
        Toast.makeText(MainActivity.this,
                ing2, Toast.LENGTH_LONG).show();
        Toast.makeText(MainActivity.this,
                ing3, Toast.LENGTH_LONG).show();
         **/
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(parent.getContext(),
        //        "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
        //        Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        final Spinner spIngredient1 = (Spinner) findViewById(R.id.ingredient1);
        final Spinner spIngredient2 = (Spinner) findViewById(R.id.ingredient2);
        final Spinner spIngredient3 = (Spinner) findViewById(R.id.ingredient3);
        Button btnSearch = (Button) findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Button btnSearch = (Button) findViewById(R.id.btnSearch);
                btnSearch.setVisibility(View.INVISIBLE);

//                Toast.makeText(MainActivity.this,
//                        "OnClickListener : " +
//                                "\nSpinner 1 : "+ String.valueOf(spIngredient1.getSelectedItem()) +
//                                "\nSpinner 2 : "+ String.valueOf(spIngredient2.getSelectedItem()),
//                        Toast.LENGTH_SHORT).show();
                retrieveRecipes(v, String.valueOf(spIngredient1.getSelectedItem()), String.valueOf((spIngredient2.getSelectedItem())),
                        String.valueOf(spIngredient3.getSelectedItem()));
            }

        });
    }
}
