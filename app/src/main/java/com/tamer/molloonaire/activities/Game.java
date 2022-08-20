package com.tamer.molloonaire.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.tamer.molloonaire.R;


import java.util.ArrayList;

public class Game extends AppCompatActivity {

    ArrayList<Question> easy , medium , hard;  // stores the question in each corresponding level

    ImageView askAudience, askFriend, changeQuestion, removeTwo; // help methods

    TextView questionTxt; // Question

    Button option1, option2, option3, option4; //Options

    int question = 1;  // represent the current question number from [1 , 15]

    public Question current = null; // current Question object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getSupportActionBar().hide();

        initQuestions(); // to initiate the dataset

        initGUI(); // to initiate the GUI
        
        getNextQuestion(); // fetch the next question

    }

    private void getNextQuestion() {


        if (question == 16){
            congratulate();
            return;
        }


        // The if is used to select the difficulty of the question
        if (question >= 1 && question <= 5)
            current = getEasyQuestion();
        else if (question >= 6 && question <= 10)
            current = getMediumQuestion();
        else
            current = getHardQuestion();

        displayTheQuestion(current);




    }

    private void congratulate() {
        Toast.makeText(this, "Congratulations", Toast.LENGTH_SHORT).show();
    }

    private void gameOver() {
        Toast.makeText(this, "Bad Luck", Toast.LENGTH_SHORT).show();
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
        option1.setText(cur);

        randIndex = (int)(Math.random() * options.size());
        cur = options.get(randIndex);
        options.remove(randIndex);
        option2.setText(cur);

        randIndex = (int)(Math.random() * options.size());
        cur = options.get(randIndex);
        options.remove(randIndex);
        option3.setText(cur);

        randIndex = (int)(Math.random() * options.size());
        cur = options.get(randIndex);
        options.remove(randIndex);
        option4.setText(cur);

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

        // buttons
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        //TextView
        questionTxt = findViewById(R.id.question);

        // Add ClickListener to the buttons

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCliked(option1.getText().toString());
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCliked(option2.getText().toString());
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCliked(option3.getText().toString());
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCliked(option4.getText().toString());
            }
        });

        //  Add ClickListener to the help methods

        changeQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeQuestion.setEnabled(false);
                getNextQuestion();
            }
        });

        removeTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeTwo.setEnabled(false);
                removeTwoWrongAnswers();
            }
        });

        askFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askFriend.setEnabled(false);
                callAFriend();
            }
        });

        askAudience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askAudience.setEnabled(false);
                goAndAskTheAudience();

            }
        });
    }

    private void goAndAskTheAudience() {
        Dialog dialog = new Dialog();

        int correctAnswerIndex = 3;
        // get the correct Answer

        if (current.getAnswer().equals(option1.getText().toString()))
            correctAnswerIndex = 0;
        else if (current.getAnswer().equals(option2.getText().toString()))
            correctAnswerIndex = 1;
        else if (current.getAnswer().equals(option3.getText().toString()))
            correctAnswerIndex = 2;

        Bundle bundle = new Bundle();
        bundle.putInt("CorrectAnswer" , correctAnswerIndex);

        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager() , "Yahia");

    }

    private void callAFriend() {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("أعتقد ان الأجابة الصحيحة هي " + current.getAnswer());
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "تمام",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void removeTwoWrongAnswers() {

        ArrayList <Button> buttonList = new ArrayList<>();

        buttonList.add(option1);
        buttonList.add(option2);
        buttonList.add(option3);
        buttonList.add(option4);

        // remove the correct answer button from the list
        if (current.getAnswer().equals(option1.getText().toString()))
            buttonList.remove(0);
        else if (current.getAnswer().equals(option2.getText().toString()))
            buttonList.remove(1);
        else if (current.getAnswer().equals(option3.getText().toString()))
            buttonList.remove(2);
        else
            buttonList.remove(3);

        // remove two incorrect Answers
        int randIndex = (int)(Math.random() * buttonList.size());
        buttonList.get(randIndex).setEnabled(false);
        buttonList.get(randIndex).setText("");
        buttonList.remove(randIndex);

        randIndex = (int)(Math.random() * buttonList.size());
        buttonList.get(randIndex).setEnabled(false);
        buttonList.get(randIndex).setText("");




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

    // this class is used to display the audience choices
    public static class Dialog extends DialogFragment
    {

        // variable for our bar chart
        BarChart barChart;

        // variable for our bar data.
        BarData barData;

        // variable for our bar data set.
        BarDataSet barDataSet;

        // array list for storing entries.
        ArrayList barEntriesArrayList;

        @NonNull
        @Override
        public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.dialog, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).
                    setView(view);

            Bundle bundle = getArguments();

            int correctAnswer = bundle.getInt("CorrectAnswer");

            initViews(view , correctAnswer);

            // creating a new bar data set.
            barDataSet = new BarDataSet(barEntriesArrayList, "Millioneer");
            // creating a new bar data and
            // passing our bar data set.
            barData = new BarData(barDataSet);

            // below line is to set data
            // to our bar chart.
            barChart.setData(barData);

            // adding color to our bar data set.
            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

            // setting text color.
            barDataSet.setValueTextColor(Color.BLACK);

            // setting text size
            barDataSet.setValueTextSize(16f);
            barChart.getDescription().setEnabled(false);

            return builder.create();
        }

        private void initViews(View view , int correctAnswer) {
            barChart = view.findViewById(R.id.idBarChart);

            // calling method to get bar entries.
            getBarEntries(correctAnswer);


        }

        private void getBarEntries(int correctAnswer) {
            // creating a new array list
            barEntriesArrayList = new ArrayList<>();

            int largestValue = (int) ((Math.random() * (20)) + 50);
            int max = largestValue - 30;
            int min = 20;
            int secondLargestValue = (int) ((Math.random() * (max - min)) + min);
            max = 100 - largestValue - secondLargestValue;
            min = 10;
            int thirdLargestValue = (int) ((Math.random() * (max - min)) + min);
            int fourthLargestValue = (100 - largestValue - secondLargestValue - thirdLargestValue);

            System.out.println(largestValue + " "+ secondLargestValue + " " + thirdLargestValue + " " + fourthLargestValue);

            switch (correctAnswer)
            {
                case 0:
                    barEntriesArrayList.add(new BarEntry(1f, largestValue));
                    barEntriesArrayList.add(new BarEntry(2f, thirdLargestValue));
                    barEntriesArrayList.add(new BarEntry(3f, fourthLargestValue));
                    barEntriesArrayList.add(new BarEntry(4f, secondLargestValue));
                    break;
                case 1:
                    barEntriesArrayList.add(new BarEntry(1f, secondLargestValue));
                    barEntriesArrayList.add(new BarEntry(2f, largestValue));
                    barEntriesArrayList.add(new BarEntry(3f, thirdLargestValue));
                    barEntriesArrayList.add(new BarEntry(4f, fourthLargestValue));
                    break;
                case 2:
                    barEntriesArrayList.add(new BarEntry(1f, secondLargestValue));
                    barEntriesArrayList.add(new BarEntry(2f, fourthLargestValue));
                    barEntriesArrayList.add(new BarEntry(3f, largestValue));
                    barEntriesArrayList.add(new BarEntry(4f, thirdLargestValue));
                    break;
                default:
                    barEntriesArrayList.add(new BarEntry(1f, thirdLargestValue));
                    barEntriesArrayList.add(new BarEntry(2f, fourthLargestValue));
                    barEntriesArrayList.add(new BarEntry(3f, secondLargestValue));
                    barEntriesArrayList.add(new BarEntry(4f, largestValue));
                    break;

            }


        }
    }
}