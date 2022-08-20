package com.tamer.molloonaire.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tamer.molloonaire.R;

import java.util.ArrayList;

public class Game extends AppCompatActivity {

    ArrayList<Question> easy , medium , hard;  // stores the question in each corresponding level

    ImageView askAudience, askFriend, changeQuestion, removeTwo; // help methods

    TextView questionTxt; // Question

    CardView option1, option2, option3, option4; // options cards

    TextView option1_content, option2_content, option3_content, option4_content; // options contents

    TextView option1_number, option2_number, option3_number, option4_number; // options numbers

    ImageView sound, scape; // scape and sound

    boolean isAnswered = false ; // to indicate if the user has answered the question or not
    
    String userAnswer = "" ; // user's answer to the current Question 

    int question = 1;  // represent the current question number from [1 , 15]

    Question current = null; // current Question object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getSupportActionBar().hide();
        initQuestions(); // to initiate the dataset

        initGUI(); // to initiate the GUI
        
        getNextQuestion();

    }

    private void getNextQuestion() {


        if (question == 16){
            congratulate();
            return;
        }


        // The if is used to select the difficulty of the question
        Toast.makeText(this, question + "", Toast.LENGTH_SHORT).show();
        if (question >= 1 && question <= 5)
            current = getEasyQuestion();
        else if (question >= 6 && question <= 10)
            current = getMediumQuestion();
        else
            current = getHardQuestion();

        displayTheQuestion(current);


        if (!userAnswer.equals(current.getAnswer())) {
            gameOver();
        }


    }

    private void congratulate() {
    }

    private void gameOver() {
    }

    public void displayTheQuestion(Question current)
    {
        // to shuffle the options every time
        ArrayList<String> options = new ArrayList<>();
        options.add(current.getOption1());
        options.add(current.getOption2());
        options.add(current.getOption3());
        options.add(current.getOption4());

        int randIndex = (int)(Math.random() * options.size());
        String cur = options.get(randIndex);
        options.remove(randIndex);
        option1_number.setText(cur);

        randIndex = (int)(Math.random() * options.size());
        cur = options.get(randIndex);
        options.remove(randIndex);
        option2_number.setText(cur);

        randIndex = (int)(Math.random() * options.size());
        cur = options.get(randIndex);
        options.remove(randIndex);
        option3_number.setText(cur);

        randIndex = (int)(Math.random() * options.size());
        cur = options.get(randIndex);
        options.remove(randIndex);
        option4_number.setText(cur);

        // to make the button enabled
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);
        option1.setEnabled(true);

        questionTxt.setText(current.getQuestion());
    }
    public void  initGUI()
    {
        // images
        askAudience = findViewById(R.id.ask_audience);
        askFriend = findViewById(R.id.ask_friend);
        changeQuestion = findViewById(R.id.change_question);
        removeTwo = findViewById(R.id.remove_two);

        // options cards
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        // options numbers textview
        option1_number = findViewById(R.id.option1_number);
        option2_number = findViewById(R.id.option2_number);
        option3_number = findViewById(R.id.option3_number);
        option4_number = findViewById(R.id.option4_number);

        // options content textview
        option1_content = findViewById(R.id.option1_content);
        option2_content = findViewById(R.id.option2_content);
        option3_content = findViewById(R.id.option3_content);
        option4_content = findViewById(R.id.option4_content);

        // sound and logout image views
        sound = findViewById(R.id.mute);
        scape = findViewById(R.id.scape);
        //TextView
        questionTxt = findViewById(R.id.question);

        // Add ClickListener to the buttons

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCliked(option1_content.getText().toString());

            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCliked(option2_content.getText().toString());
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCliked(option3_content.getText().toString());
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCliked(option4_content.getText().toString());
            }
        });
    }

    private void btnCliked(String text) {
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);
        if (text.equals(current.getAnswer()))
        {
            question++;
            getNextQuestion();
        }else
        {
            gameOver();
        }
    }

    public Question getEasyQuestion()
    {
        int random = (int)(Math.random() * easy.size());
        Question current = easy.get(random);
        easy.remove(random);
        return current;
    }

    public Question getMediumQuestion()
    {
        int random = (int)(Math.random() * medium.size());
        Question current = medium.get(random);
        medium.remove(random);
        return current;
    }

    public Question getHardQuestion()
    {
        int random = (int)(Math.random() * hard.size());
        Question current = hard.get(random);
        hard.remove(random);
        return current;
    }
    public void initQuestions()
    {
        easy = new ArrayList<>();
        medium = new ArrayList<>();
        hard = new ArrayList<>();

        easy.add(new Question( "ما هو البيت الذي لا يمتلك أعمدة ولا أبوابًا ولا نوافذ؟" , "بيت الدب القطبي" , "بيت النحل."  , "بيت الأسد"  , "بيت الشعر" , "بيت الشعر" ));
        easy.add(new Question( "ما هو أصل الطبق المسمى شوربة البصل أو (بالإنجليزية: union Soup) ؟" , "أسباني" , "ألماني"  , "انجليزي"  , "فرنسي" , "فرنسي" ));
        easy.add(new Question( "ما هو الشيء الذي إذا ترك في الماء مات بالرغم من أنه يصنع من الماء؟" , "النار" , "الثلج"  , "الملح"  , "السكر" , "الثلج" ));
        easy.add(new Question( "شيء يظهر في القرن مرة وفي الدقيقة مرتين ولا يظهر بالساعة؟" , "القاف" , "السين"  , "العين"  , "اللام" , "القاف" ));
        easy.add(new Question( "أين يقع نهري دجلة والفرات؟" , "مصر" , "العراق"  , "سوريا"  , "الأردن" , "العراق" ));
        easy.add(new Question( "ما اسم النهر الموجود في مصر ؟" , "دجلة" , "النيل"  , "الفرات"  , "الأمازون" , "النيل" ));
        easy.add(new Question( "كم عدد أبواب الجنة؟" , "6" , "7"  , "8"  , "9" , "8" ));
        easy.add(new Question( "كم عدد أبواب وطبقات جهنم؟" , "6" , "7"  , "8"  , "9" , "7" ));


        medium.add(new Question( "من هو أول صحابي حيّا الرسول صلى الله عليه وسلم بتحية الإسلام ؟" , "أبو بكر الصديق" , "أبو أيوب الأنصاري"  , "أبو ذر الغفاري"  , "أبو موسى الأشعري" , "أبو ذر الغفاري" ));
        medium.add(new Question( "كم عدد الدول العربية المطلة على البحر الأبيض المتوسط؟" , "4" , "5"  , "8"  , "11" , "8" ));
        medium.add(new Question( "ما هو البحر الذي يسمى بحر القلزم؟" , "البحر العربي" , "البحر الأحمر"  , "بحر قزوين"  , "البحر الأسود" , "البحر العربي" ));
        medium.add(new Question( "متي وقعت غزوة خيبر؟" , "عام 2 هجريا" , "عام 7 هجريا"  , "عام 6 هجريا"  , "عام 8 هجريا" , "عام 7 هجريا" ));
        medium.add(new Question( "ما هي عاصمة إيران؟" , "طهران" , "تبليسي"  , "إسلام أباد"  , "أنقرة" , "طهران" ));
        medium.add(new Question( "ما اسم أكبر خليج في العالم ؟" , "المكسيك" , "البنغال"  , "العربي"  , "أمبراكيكوس" , "البنغال" ));
        medium.add(new Question( "ما اسم المدينة التي أسسها جوهر الصقلي؟" , "البصرة" , "بغداد"  , "الكوفة"  , "القاهرة" , "القاهرة" ));
        medium.add(new Question( "ما هي عاصمة السنغال؟" , "داكار" , "أبيدجان"  , "سكوبيا"  , "كينشاسا" , "داكار" ));
        medium.add(new Question( "من من بنات النبي محمد صلى الله عليه وسلم التي توفت أولاً؟" , "السيدة فاطمة" , "السيدة رقية"  , "السيدة زينب"  , "السيدة أم كلثوم" , "السيدة رقية" ));
        medium.add(new Question( "في أي عام قامت الحرب العالمية الثانية؟ " , "1934" , "1939"  , "1940"  , "1945" , "1939" ));
        medium.add(new Question( "متى قامت ثورة الضباط الأحرار في مصر؟" , "1950" , "1919"  , "1930"  , "1952" , "1952" ));
        medium.add(new Question( "كم عام استمر حكم الخلافة العثمانية؟ " , "100" , "200"  , "300"  , "400" , "400" ));

        hard.add(new Question( "إلى أي محيط ينتمي بحر العرب؟" , "الهادي " , "الهندي"  , "الأطلنطي"  , "المحيط المتجمد الشمالي" , "الهندي" ));
        hard.add(new Question( "النحلة ترفرف بجناحيها في الثانية الواحدة بمعدل….؟" , "100" , "250"  , "350"  , "400" , "350" ));
        hard.add(new Question( "أين كان أول مكان اخترع فيه البارود؟" , "الروم" , "الصين"  , "الفرس"  , "العرب" , "الصين" ));
        hard.add(new Question( "ما هي عاصمة ألبانيا؟" , "تيرانا" , "أنقرة"  , "ساوتومي"  , "كتمندو" , "تيرانا" ));
        hard.add(new Question( "من هو صاحب لقب الشيخ الرئيس؟" , "ابن سيناء" , "سقراط"  , "الخوازمي"  , "ابن بطوط" , "ابن سيناء" ));
        hard.add(new Question( "من هو الشاعر الذي ألف كتاب الحماسة وكتاب فحول الشعراء؟" , "أبو تمام" , "نزار القباني"  , "بشار بن برد"  , "أحمد شوقي" , "أبو تمام" ));
        hard.add(new Question( "من هو الشاعر الذي يُلقب بالمرعث؟" , "أبو تمام" , "نزار القباني"  , "بشار بن برد"  , "أحمد شوقي" , "بشار بن برد" ));
        hard.add(new Question( "من هو الأديب العربي مؤلف مسرحية ” يا طالع الشجرة ” ؟" , "توفيق الحكيم" , "عباس العقاد"  , "طه حسين"  , "محمد عبد الوهاب" , "توفيق الحكيم" ));
        hard.add(new Question( "ما هي المادة التي يستخرج منها الفازلين؟" , "المطاط" , "البرافين"  , "النفط"  , "الكوك" , "النفط" ));
        hard.add(new Question( "ما هي جنسية مخترع المنفاخ أو المضخة الهوائية للعالم أتوفون جريكي؟" , "أسباني" , "ألماني"  , "دنماركي"  , "هولندي" , "ألماني" ));
        hard.add(new Question( "كم عدد فقرات رقبة الزرافة؟" , "7" , "8"  , "9"  , "10" , "7" ));
        hard.add(new Question( "ما هو الطائر الوحيد الذي يستطيع أن يميز اللون الأزرق؟" , "البومة" , "الصقر"  , "النسر"  , "العصفور" , "البومة" ));
        hard.add(new Question( "كم عدد الأسنان التي يمتلكها الجمل؟ " , "32" , "33"  , "34"  , "35" , "34" ));
        hard.add(new Question( "كم يبلغ عدد الأحبال الصوتية لدى الإنسان؟ " , "3" , "4"  , "5"  , "6" , "4" ));
        hard.add(new Question( "متى أنفجرت مركبة الفضاء هي تشالنجر؟ " , "1986" , "1987"  , "1988"  , "1989" , "1986" ));
    }
}