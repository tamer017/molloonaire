package com.tamer.molloonaire.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tamer.molloonaire.R;

import java.util.ArrayList;

public class Game extends AppCompatActivity {

    ArrayList<Question> easy , medium , hard;  // stores the question in each corresponding level

    int question = 1;  // represent the current question number from [1 , 15]
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initQuestions(); // to initiate the dataset

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