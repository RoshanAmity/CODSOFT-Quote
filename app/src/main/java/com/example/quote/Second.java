package com.example.quote;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.util.Random;

public class Second extends AppCompatActivity {

    TextView quote;
    ImageView favourite;

    MaterialButton favouriteList, Share;
    QuotesData quotesData;
    boolean isFavourite = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        quote = findViewById(R.id.quote);
        favourite = findViewById(R.id.favorite);
        favouriteList = findViewById(R.id.favoriteButton);
        Share = findViewById(R.id.share);

        randomQuote();

        favourite.setOnClickListener(v -> {
            String quoteText = quote.getText().toString();
            save(quoteText);
            Drawable drawable = ContextCompat.getDrawable(this,R.drawable.colorheart);
            favourite.setImageDrawable(drawable);
        });

        favouriteList.setOnClickListener(v -> {
            shift();
        });

        Share.setOnClickListener(v -> {
            String quoteText = quote.getText().toString();
            shareQuote(quoteText);
        });
    }

    private void randomQuote(){
        String[] arr = {"The only way to do great work is to love what you do.",
                "Success is not final, failure is not fatal: It is the courage to continue that counts.",
                "Believe you can and you're halfway there.",
                "You are never too old to set another goal or to dream a new dream.",
                "The purpose of life is not to be happy. It is to be useful, to be honorable, to be compassionate, to have it make some difference that you have lived and lived well.",
                "Doubt is a detour, not a dead end. ",
                "The best revenge is massive success.",
                "Even the smallest spark can ignite a fire.",
                "The future belongs to those who believe in the beauty of their dreams.",
                "The journey of a thousand miles begins with a single step.",
                "Happiness is a butterfly, the more you chase it, the more it eludes you, but if you turn your attention to other things, it will come softly and sit on your shoulder.",
                "The only person you are destined to become is the person you decide to be.",
                "The best and most beautiful things in the world cannot be seen or even touched - they must be felt with the heart.",
                "It is during our darkest moments that we must focus to see the light.",
                "Education is the passport to the future, for tomorrow belongs to those who prepare for it today.",
                "The purpose of life is not to be happy. It is to be useful, to be honorable, to be compassionate, to have it make some difference that you have lived and lived well.",
                "Twenty years from now you will be more disappointed by the things that you didn’t do than by the ones you did do.",
                "The best and most beautiful things in the world cannot be seen or even touched - they must be felt with the heart.",
                "Let us never underestimate the power of a dream and the importance of fighting for it.",
                "Education is the most powerful weapon which you can use to change the world.",
                "The best and most beautiful things in the world cannot be seen or even touched - they must be felt with the heart. ",
                "The only person you are destined to become is the person you decide to be.",
                "Helping one person might not change the world, but it could change the world for that one person.",
                "A river cuts through rock, not because of its power, but because of its persistence.",
                "Challenges are what make life interesting and overcoming them is what makes life meaningful.",
                "You are braver than you believe, stronger than you seem, and smarter than you think.",
                "The man who moves a mountain begins by carrying away small stones.",
                "Don't be pushed around by the fears in your mind. Be led by the dreams in your heart.",
                "You are braver than you believe, stronger than you seem, and smarter than you think.",
                "The harder you work for something, the greater you'll feel when you achieve it.",
                "Believe in yourself and all that you are. Know that there is something inside you that is greater than any obstacle.",
                "People who are crazy enough to think they can change the world, are the ones who do.",
                "You can either experience the pain of discipline or the pain of regret. The choice is yours.",
                "Be not afraid of life. Believe that life is worth living, and your belief will help create the fact.",
                "The greatest glory in living lies not in never falling, but in rising every time we fall.",
                "Do not go where the path may lead, go instead where there is no path and leave a trail.",
                "Happiness is not something ready made. It comes from your own actions.",
                "Your time is limited, don't waste it living someone else's life.",
                "In the end, it's not the years in your life that count. It's the life in your years.",
                "Life is what happens when you're busy making other plans.",
                "It is our choices that show what we truly are, far more than our abilities.",
                "You have within you right now, everything you need to deal with whatever the world can throw at you.",
                "Don't be pushed around by the fears in your mind. Be led by the dreams in your heart.",
                "It does not matter how slowly you go as long as you do not stop.",
                "The only limit to our realization of tomorrow is our doubts of today.",
                "What you get by achieving your goals is not as important as what you become by achieving your goals.",
                "Act as if what you do makes a difference. It does.",
                "Don't watch the clock; do what it does. Keep going.",
                "Keep your face always toward the sunshine—and shadows will fall behind you.",
                "What lies behind us and what lies before us are tiny matters compared to what lies within us.",
                "Life is 10% what happens to us and 90% how we react to it.",
                "The purpose of our lives is to be happy."
        };

        Random random = new Random();

        int limit = 52;
        int randomNumber = random.nextInt(limit);
        String quoteof = arr[randomNumber];
        quote.setText(quoteof);
    }

    public void save(String quoteText){
        Toast.makeText(Second.this, "Save", Toast.LENGTH_SHORT).show();

        SQLite sqLite = new SQLite(Second.this);
        try {
            quotesData = new QuotesData(1, quoteText);
        }
        catch (Exception e) {
            Toast.makeText(Second.this, "Error Occur" + e.toString(), Toast.LENGTH_LONG).show();
        }

        boolean status = sqLite.Enterly(quotesData);



    }

    public void shift(){
        Intent intent = new Intent(Second.this, Favourite.class);
        startActivity(intent);
    }

    public void shareQuote(String quotetext){
        Intent shareintent = new Intent(Intent.ACTION_SEND);
        shareintent.putExtra(Intent.EXTRA_TEXT, quotetext);
       shareintent.setType("text/plain");
        startActivity(Intent.createChooser(shareintent,"Share via"));
    }
}
