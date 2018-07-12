    import javax.swing.*;
    import java.awt.*;  
    import java.awt.event.*;
    import java.io.*;
    import java.lang.*;  
    import java.util.StringTokenizer;
    import java.util.*;
    import javax.swing.border.*;
    import java.net.*;
	import java.io.IOException;
	import java.net.URL;
	import javax.media.*;
	import javax.swing.event.*;
	import javax.media.control.FramePositioningControl;	
	
	//	main class
	class PMVS
	{
	public static void main(String[]args)  
    {
	GUI g=new GUI();
	}
	}

	//	GUI
	class GUI extends JFrame implements ActionListener
    {
	//	Declaration
	//	Frame declaration
	JFrame main_frame=new JFrame("Personalized Movie Summarizer");  
	
	//	Panel declaration
	JPanel actor_panel = new JPanel();
	JPanel event_panel = new JPanel();
	JPanel concept_panel = new JPanel();
	JPanel constraint_panel = new JPanel();
	
	JPanel first_half_panel=new JPanel();
	JPanel second_half_panel=new JPanel();
	JPanel first_quarter_panel=new JPanel();
	JPanel fourth_quarter_panel=new JPanel();
		
	// Video player declaration
     MediaPanel video_panel= new MediaPanel();
	 
	//	Control declaration
	JLabel actor_empty_label1=new JLabel("");
	JLabel actor_empty_label2=new JLabel("");
	JLabel event_empty_label1=new JLabel("");
	JLabel event_empty_label2=new JLabel("");
	JComboBox movie_combobox;
	JLabel[] actor_label=new JLabel[4];
	JSlider[] actor_slider=new JSlider[4];
	JCheckBox actor_disable_checkbox=new JCheckBox("Disable");
	
	JLabel event_empty_label=new JLabel("");
	JLabel[] event_label=new JLabel[6];
	JSlider[] event_slider=new JSlider[6];
	JCheckBox event_disable_checkbox=new JCheckBox("Disable");
	
	JLabel concept_empty_label=new JLabel("");
	JLabel[] concept_label=new JLabel[19];
	JSlider[] concept_slider=new JSlider[19];
	JCheckBox concept_disable_checkbox=new JCheckBox("Disable");
	
	JRadioButton summary_time_radiobutton;
	JTextField summary_time_textfield;
	JRadioButton skimming_ratio_radiobutton;
	JSlider skimming_ratio_slider;
	ButtonGroup summary_time_buttonGroup;
	
	JRadioButton shot_level_radiobutton;
	JRadioButton scene_level_radiobutton;
	ButtonGroup summary_element_buttonGroup;
	
	JRadioButton rank_order_radiobutton;
	JRadioButton original_order_radiobutton;
	ButtonGroup summary_order_buttonGroup;
	
	JRadioButton original_audio_radiobutton;
	JRadioButton music_audio_radiobutton;
	JRadioButton mute_audio_radiobutton;
	ButtonGroup audio_buttonGroup;
	
	JTextField keywords_textfield=new JTextField();
	JCheckBox keywords_checkbox=new JCheckBox("Disable");
	JButton summary_button =new JButton("Summarize");
	StarRater starRater = new StarRater(5, 5, 5);
	JPanel rater_panel=new JPanel();
	
	String movie[]={"Men In Black","Hancock","Wall-E"};
	String movie_name;
	String actor[]={"Actor 1","Actor 2","Actor 3","Actor 4"};
	String event[]={"Action","Comedy","Dance","Romance","Party","Wedding"};
	String concept[]={"Beach","Blue Sky","Building","Flowers","Greenery","People",
	"Indoors","Infant","Landmark","Mountains","Nature","Outdoors","Pet","Skyline",
	"Sport","Sunset","Urbanism","Vehicles","Water"};
	
	float[] actor_preference={0,0,0,0};
	float[] event_preference={0,0,0,0,0,0};
	float[] concept_preference={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	float skimming_ratio;
	Computer com1=new Computer();
	//	Initialization
	
	public GUI()
	{
	
	//	Initialization
	JComboBox movie_combobox = new JComboBox(movie);
	movie_combobox.setForeground(Color.decode("#8B008B"));
    movie_combobox.setBorder( new TitledBorder("Movie Name"));
	summary_button.setForeground(Color.decode("#8B008B"));
	
	//	Actor Panel
	JPanel[] actor_panel_sub=new JPanel[4];
		for(int i=0;i<4;i++)
		{
		actor_slider[i] = new JSlider();
		actor_slider[i].setValue(0);
		actor_label[i] = new JLabel("  "+actor[i]);
		actor_panel_sub[i]=new JPanel();
		actor_label[i].setForeground(Color.decode("#8B008B"));
		actor_slider[i].setForeground(Color.decode("#8B008B"));
		}
		
	actor_panel.setLayout(new GridLayout(3,2));
	 
	 for(int i=0;i<4;i++)
		{
		actor_panel_sub[i].setLayout(new GridLayout(0,2));
		actor_panel_sub[i].add(actor_label[i]);
		actor_panel_sub[i].add(actor_slider[i]);
		actor_panel.add(actor_panel_sub[i]);
		}
		actor_disable_checkbox.setForeground(Color.decode("#8B008B"));
		actor_panel.add(actor_empty_label1);
		JPanel actor_empty_panel=new JPanel();
		actor_empty_panel.setLayout(new GridLayout(1,2));
		actor_empty_panel.add(actor_empty_label2);
		actor_empty_panel.add(actor_disable_checkbox);
		actor_panel.add(actor_empty_panel);
		actor_panel.setBorder(new TitledBorder("Actors"));
	
	//	Event Panel
	JPanel[] event_panel_sub=new JPanel[8];
		for(int i=0;i<6;i++)
		{
		event_slider[i] = new JSlider();
		event_slider[i].setValue(0);
		event_label[i] = new JLabel("  "+event[i]);
		event_panel_sub[i]=new JPanel();
		event_label[i].setForeground(Color.decode("#8B008B"));
		event_slider[i].setForeground(Color.decode("#8B008B"));
		}
		
	 event_panel.setLayout(new GridLayout(4,2));
	 
	 for(int i=0;i<6;i++)
		{
		event_panel_sub[i].setLayout(new GridLayout(0,2));
		event_panel_sub[i].add(event_label[i]);
		event_panel_sub[i].add(event_slider[i]);
		event_panel.add(event_panel_sub[i]);
		}
		event_disable_checkbox.setForeground(Color.decode("#8B008B"));
		event_panel.add(event_empty_label1);
		JPanel event_empty_panel=new JPanel();
		event_empty_panel.setLayout(new GridLayout(1,2));
		event_empty_panel.add(event_empty_label2);
		event_empty_panel.add(event_disable_checkbox);
		event_panel.add(event_empty_panel);
        event_panel.setBorder(new TitledBorder("Events"));
	
	//	Concept Panel
	JPanel[] concept_panel_sub=new JPanel[20];
		for(int i=0;i<19;i++)
		{
		concept_slider[i] = new JSlider();
		concept_slider[i].setValue(0);
		concept_label[i] = new JLabel("  "+concept[i]);
		concept_panel_sub[i]=new JPanel();
		concept_label[i].setForeground(Color.decode("#8B008B"));
		concept_slider[i].setForeground(Color.decode("#8B008B"));
		}
		
	 concept_panel.setLayout(new GridLayout(10,2));
	 
	 for(int i=0;i<19;i++)
		{
		concept_panel_sub[i].setLayout(new GridLayout(0,2));
		concept_panel_sub[i].add(concept_label[i]);
		concept_panel_sub[i].add(concept_slider[i]);
		concept_panel.add(concept_panel_sub[i]);
		}
		concept_disable_checkbox.setForeground(Color.decode("#8B008B"));
		JPanel concept_empty_panel=new JPanel();
		concept_empty_panel.setLayout(new GridLayout(1,2));
		concept_empty_panel.add(concept_empty_label);
		concept_empty_panel.add(concept_disable_checkbox);
		concept_panel.add(concept_empty_panel);
    concept_panel.setBorder(new TitledBorder("Concepts"));
	
	//	Constraint Panel
	constraint_panel.setBorder(new TitledBorder("Constraints"));
	{
	//	Summary Time Panel
	JPanel summary_time_panel=new JPanel();
	JPanel skimming_ratio_panel=new JPanel();
	summary_time_radiobutton=new JRadioButton("Summary Time (in mins)");
	summary_time_radiobutton.setForeground(Color.decode("#8B008B"));
	summary_time_radiobutton.setSelected(true);
	summary_time_textfield=new JTextField();
	summary_time_textfield.setBackground(Color.decode("#E8E8E8"));
	skimming_ratio_radiobutton=new JRadioButton("Skimming Ratio ");
	skimming_ratio_radiobutton.setForeground(Color.decode("#8B008B"));
	skimming_ratio_slider=new JSlider();
	skimming_ratio_slider.setValue(0);
	summary_time_buttonGroup = new ButtonGroup();
	summary_time_buttonGroup.add(summary_time_radiobutton);
	summary_time_buttonGroup.add(skimming_ratio_radiobutton);

	summary_time_panel.setLayout(new GridLayout(1,2));
	skimming_ratio_panel.setLayout(new GridLayout(1,2));
	summary_time_panel.add(summary_time_radiobutton);
	summary_time_panel.add(summary_time_textfield);
	skimming_ratio_panel.add(skimming_ratio_radiobutton);
	skimming_ratio_panel.add(skimming_ratio_slider);
	
	//	Summary element Panel
	JPanel summary_element_panel=new JPanel();
	shot_level_radiobutton=new JRadioButton("Play Shots");
	shot_level_radiobutton.setForeground(Color.decode("#8B008B"));
	shot_level_radiobutton.setSelected(true);
	scene_level_radiobutton=new JRadioButton("Play Scenes");
	scene_level_radiobutton.setForeground(Color.decode("#8B008B"));
	summary_element_buttonGroup = new ButtonGroup();
	summary_element_buttonGroup.add(shot_level_radiobutton);
	summary_element_buttonGroup.add(scene_level_radiobutton);
	
	summary_element_panel.setLayout(new GridLayout(1,2));
	summary_element_panel.add(shot_level_radiobutton);
	summary_element_panel.add(scene_level_radiobutton);
	
	//	Summary Order Panel
	JPanel summary_order_panel=new JPanel();
	rank_order_radiobutton=new JRadioButton("Play Ranked Order");
	rank_order_radiobutton.setForeground(Color.decode("#8B008B"));
	rank_order_radiobutton.setSelected(true);
	original_order_radiobutton=new JRadioButton("Play Original Order");
	original_order_radiobutton.setForeground(Color.decode("#8B008B"));
	summary_order_buttonGroup = new ButtonGroup();
	summary_order_buttonGroup.add(rank_order_radiobutton);
	summary_order_buttonGroup.add(original_order_radiobutton);
	
	summary_order_panel.setLayout(new GridLayout(1,2));
	summary_order_panel.add(rank_order_radiobutton);
	summary_order_panel.add(original_order_radiobutton);
	
	//	Summary Audio Panel
	JPanel summary_audio_panel=new JPanel();
	original_audio_radiobutton=new JRadioButton("Play Original Audio");
	original_audio_radiobutton.setForeground(Color.decode("#8B008B"));	
	original_audio_radiobutton.setSelected(true);
	music_audio_radiobutton=new JRadioButton("Play Music");
	music_audio_radiobutton.setForeground(Color.decode("#8B008B"));
	mute_audio_radiobutton=new JRadioButton("Mute");
	mute_audio_radiobutton.setForeground(Color.decode("#8B008B"));
	audio_buttonGroup = new ButtonGroup();
	audio_buttonGroup.add(original_audio_radiobutton);
	audio_buttonGroup.add(music_audio_radiobutton);
	audio_buttonGroup.add(mute_audio_radiobutton);
	
	summary_audio_panel.setLayout(new GridLayout(1,3));
	summary_audio_panel.add(original_audio_radiobutton);
	summary_audio_panel.add(music_audio_radiobutton);
	summary_audio_panel.add(music_audio_radiobutton);
	summary_audio_panel.add(mute_audio_radiobutton);
	
	constraint_panel.setLayout(new GridLayout(5,1));
	constraint_panel.add(summary_time_panel);
	constraint_panel.add(skimming_ratio_panel);
	constraint_panel.add(summary_element_panel);
	constraint_panel.add(summary_order_panel);
	constraint_panel.add(summary_audio_panel);
	}
	
	//	Keywords, Button and Ratings panel
	
	JPanel butrat_panel=new JPanel();
	rater_panel.add(starRater);
	rater_panel.setBorder( new TitledBorder("Summary Ratings"));
	butrat_panel.setLayout(new GridLayout(2,1));
	butrat_panel.add(summary_button);
	butrat_panel.add(rater_panel);
	keywords_textfield.setBorder( new TitledBorder("Keywords"));
	keywords_textfield.setBackground(Color.decode("#E8E8E8"));
	JPanel keybutrat_panel=new JPanel();
	keybutrat_panel.setLayout(new GridLayout(1,2));
	keybutrat_panel.add(keywords_textfield);
	keybutrat_panel.add(butrat_panel);
	
	//	Adding to Quarter Panels
	first_quarter_panel.setLayout(new GridLayout(3,1));
	first_quarter_panel.add(movie_combobox);
	first_quarter_panel.add(actor_panel);
	first_quarter_panel.add(event_panel);
	fourth_quarter_panel.setLayout(new GridLayout(2,1));
	fourth_quarter_panel.add(constraint_panel);
	fourth_quarter_panel.add(keybutrat_panel);
	
	//	Adding to Halfs
	first_half_panel.setLayout(new GridLayout(2,1));
	first_half_panel.add(first_quarter_panel);
	first_half_panel.add(concept_panel);
	second_half_panel.setLayout(new GridLayout(2,1));
	second_half_panel.add(video_panel);
	second_half_panel.add(fourth_quarter_panel);
	
	//	Adding to Main Frame
	main_frame.setLayout(new GridLayout(1,2));
	main_frame.add(first_half_panel);
	main_frame.add(second_half_panel);
	
	 main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
     main_frame.setSize(850,700);  
     main_frame.setVisible(true);  
	 
	//	Adding Listeners
	//	Listener for Movie Name
     movie_combobox.addActionListener(this);
     summary_button.addActionListener(this);
	 
	//	Adding listeners to sliders
	 actor_slider[0].addChangeListener(new MyChangeActor0());
	 actor_slider[1].addChangeListener(new MyChangeActor1());
	 actor_slider[2].addChangeListener(new MyChangeActor2());
	 actor_slider[3].addChangeListener(new MyChangeActor3());
	 
	 event_slider[0].addChangeListener(new MyChangeEvent0());
	 event_slider[1].addChangeListener(new MyChangeEvent1());
	 event_slider[2].addChangeListener(new MyChangeEvent2());
	 event_slider[3].addChangeListener(new MyChangeEvent3());
	 event_slider[4].addChangeListener(new MyChangeEvent4());
	 event_slider[5].addChangeListener(new MyChangeEvent5());
	 
	 concept_slider[0].addChangeListener(new MyChangeConcept0());
	 concept_slider[1].addChangeListener(new MyChangeConcept1());
	 concept_slider[2].addChangeListener(new MyChangeConcept2());
	 concept_slider[3].addChangeListener(new MyChangeConcept3());
	 concept_slider[4].addChangeListener(new MyChangeConcept4());
	 concept_slider[5].addChangeListener(new MyChangeConcept5());
	 concept_slider[6].addChangeListener(new MyChangeConcept6());
	 concept_slider[7].addChangeListener(new MyChangeConcept7());
	 concept_slider[8].addChangeListener(new MyChangeConcept8());
	 concept_slider[9].addChangeListener(new MyChangeConcept9());
	 concept_slider[10].addChangeListener(new MyChangeConcept10());
	 concept_slider[11].addChangeListener(new MyChangeConcept11());
	 concept_slider[12].addChangeListener(new MyChangeConcept12());
	 concept_slider[13].addChangeListener(new MyChangeConcept13());
	 concept_slider[14].addChangeListener(new MyChangeConcept14());
	 concept_slider[15].addChangeListener(new MyChangeConcept15());
	 concept_slider[16].addChangeListener(new MyChangeConcept16());
	 concept_slider[17].addChangeListener(new MyChangeConcept17());
	 concept_slider[18].addChangeListener(new MyChangeConcept18());
	 
	 skimming_ratio_slider.addChangeListener(new MyChangeRatio1());
	}
	String[] actor_name={"","","",""}; 
	
	//	Changing actor names
	public void actionPerformed(ActionEvent ae) 
	{
	if(ae.getActionCommand()=="comboBoxChanged")
	{
	JComboBox movie_name_object = (JComboBox) ae.getSource();
	movie_name = (String) movie_name_object.getSelectedItem();
	String actor_csvfile="E:/Movies/"+movie_name+"/actor.csv";
	int i=0;
	try
	{
    BufferedReader br = new BufferedReader(new FileReader(actor_csvfile));
    StringTokenizer st = null;
	String temp="";
	temp=br.readLine();
    st = new StringTokenizer(temp, ",");
	 while (st.hasMoreTokens()) 
	{
	actor_name[i]=st.nextToken();
	i++;
	}
	}
	catch(Exception e)
	{
	System.out.println(e);
	}
	for(i=0;i<4;i++)
	{
	actor_label[i].setText(" "+actor_name[i]);
	}
	//	calling functions to load face, event, concept and shots information
	com1.load_shot_files(movie_name);
	com1.load_actor_files(movie_name);
	com1.load_event_files(movie_name);
	com1.load_concept_files(movie_name);
	com1.load_scene_files(movie_name);
	com1.load_transcript_files(movie_name);
	}
	
	if (ae.getActionCommand().equals("Summarize")) 
	{	
	 String summary_length=summary_time_textfield.getText();
	 String keywords_preference=keywords_textfield.getText();
	 try
	 {
	 int summary_length_int;
	 int summary_element_flag=0;
	 int summary_order_flag=0;
	 summary_length_int=Integer.parseInt(summary_length)*60;
	 //	Radiobuttons flags
	 if(shot_level_radiobutton.isSelected())
	 summary_element_flag=0;
	 if(scene_level_radiobutton.isSelected())
	 summary_element_flag=1;
	 
	 if(rank_order_radiobutton.isSelected())
	 summary_order_flag=0;
	 if(original_order_radiobutton.isSelected())
	 summary_order_flag=1;
	 
	 //	Calling similarity matching function
	 com1.matcher(summary_length_int,actor_preference,event_preference,concept_preference,keywords_preference,summary_element_flag,summary_order_flag);
	 try
	 {	
	 //	Calling the player
	 File file=new File(com1.movie_URL);
	 URL url1=null;
	 url1=file.toURL();
	 video_panel.play_video(url1,com1.segment_address);
	 main_frame.validate();
	 }
	 catch ( MalformedURLException malformedURLException )
     {
            System.err.println( "Could not create URL for the file" );
     }
	 }
	 catch(Exception e)
	 {
	 System.out.println(e);
	 }
    }
    }
	//	Listeners for sliders
	public class MyChangeActor0 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = actor_slider[0].getValue(); String str = Integer.toString(value);
	actor_label[0].setText(" "+actor_name[0]+"   "+str+" %");actor_preference[0]=(float)value/100;	}}
	public class MyChangeActor1 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = actor_slider[1].getValue(); String str = Integer.toString(value);
	actor_label[1].setText(" "+actor_name[1]+"   "+str+" %");actor_preference[1]=(float)value/100;	}}
	public class MyChangeActor2 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = actor_slider[2].getValue(); String str = Integer.toString(value);
	actor_label[2].setText(" "+actor_name[2]+"   "+str+" %");actor_preference[2]=(float)value/100;	}}
	public class MyChangeActor3 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = actor_slider[3].getValue(); String str = Integer.toString(value);
	actor_label[3].setText(" "+actor_name[3]+"   "+str+" %");actor_preference[3]=(float)value/100;	}}
	
	public class MyChangeEvent0 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = event_slider[0].getValue(); String str = Integer.toString(value);
	event_label[0].setText("  "+event[0]+"   "+str+" %");event_preference[0]=(float)value/100;	}}
	public class MyChangeEvent1 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = event_slider[1].getValue(); String str = Integer.toString(value);
	event_label[1].setText("  "+event[1]+"   "+str+" %");event_preference[1]=(float)value/100;	}}
	public class MyChangeEvent2 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = event_slider[2].getValue(); String str = Integer.toString(value);
	event_label[2].setText("  "+event[2]+"   "+str+" %");event_preference[2]=(float)value/100;	}}
	public class MyChangeEvent3 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = event_slider[3].getValue(); String str = Integer.toString(value);
	event_label[3].setText("  "+event[3]+"   "+str+" %");event_preference[3]=(float)value/100;	}}
	public class MyChangeEvent4 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = event_slider[4].getValue(); String str = Integer.toString(value);
	event_label[4].setText("  "+event[4]+"   "+str+" %");event_preference[4]=(float)value/100;	}}
	public class MyChangeEvent5 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = event_slider[5].getValue(); String str = Integer.toString(value);
	event_label[5].setText("  "+event[5]+"   "+str+" %");event_preference[5]=(float)value/100;	}}
	
	public class MyChangeConcept0 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = concept_slider[0].getValue(); String str = Integer.toString(value);
	concept_label[0].setText("  "+concept[0]+"   "+str+" %");concept_preference[0]=(float)value/100;	}}
	public class MyChangeConcept1 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = concept_slider[1].getValue(); String str = Integer.toString(value);
	concept_label[1].setText("  "+concept[1]+"   "+str+" %");concept_preference[1]=(float)value/100;	}}
	public class MyChangeConcept2 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = concept_slider[2].getValue(); String str = Integer.toString(value);
	concept_label[2].setText("  "+concept[2]+"   "+str+" %");concept_preference[2]=(float)value/100;	}}
	public class MyChangeConcept3 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = concept_slider[3].getValue(); String str = Integer.toString(value);
	concept_label[3].setText("  "+concept[3]+"   "+str+" %");concept_preference[3]=(float)value/100;	}}
	public class MyChangeConcept4 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = concept_slider[4].getValue(); String str = Integer.toString(value);
	concept_label[4].setText("  "+concept[4]+"   "+str+" %");concept_preference[4]=(float)value/100;	}}
	public class MyChangeConcept5 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = concept_slider[5].getValue(); String str = Integer.toString(value);
	concept_label[5].setText("  "+concept[5]+"   "+str+" %");concept_preference[5]=(float)value/100;	}}
	public class MyChangeConcept6 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = concept_slider[6].getValue(); String str = Integer.toString(value);
	concept_label[6].setText("  "+concept[6]+"   "+str+" %");concept_preference[6]=(float)value/100;	}}
	public class MyChangeConcept7 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = concept_slider[7].getValue(); String str = Integer.toString(value);
	concept_label[7].setText("  "+concept[7]+"   "+str+" %");concept_preference[7]=(float)value/100;	}}
	public class MyChangeConcept8 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = concept_slider[8].getValue(); String str = Integer.toString(value);
	concept_label[8].setText("  "+concept[8]+"   "+str+" %");concept_preference[8]=(float)value/100;	}}
	public class MyChangeConcept9 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = concept_slider[9].getValue(); String str = Integer.toString(value);
	concept_label[9].setText("  "+concept[9]+"   "+str+" %");concept_preference[9]=(float)value/100;	}}
	public class MyChangeConcept10 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = concept_slider[10].getValue(); String str = Integer.toString(value);
	concept_label[10].setText("  "+concept[10]+"   "+str+" %");concept_preference[10]=(float)value/100;	}}
	public class MyChangeConcept11 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = concept_slider[11].getValue(); String str = Integer.toString(value);
	concept_label[11].setText("  "+concept[11]+"   "+str+" %");concept_preference[11]=(float)value/100;	}}
	public class MyChangeConcept12 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = concept_slider[12].getValue(); String str = Integer.toString(value);
	concept_label[12].setText("  "+concept[12]+"   "+str+" %");concept_preference[12]=(float)value/100;	}}
	public class MyChangeConcept13 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = concept_slider[13].getValue(); String str = Integer.toString(value);
	concept_label[13].setText("  "+concept[13]+"   "+str+" %");concept_preference[13]=(float)value/100;	}}
	public class MyChangeConcept14 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = concept_slider[14].getValue(); String str = Integer.toString(value);
	concept_label[14].setText("  "+concept[14]+"   "+str+" %");concept_preference[14]=(float)value/100;	}}
	public class MyChangeConcept15 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = concept_slider[15].getValue(); String str = Integer.toString(value);
	concept_label[15].setText("  "+concept[15]+"   "+str+" %");concept_preference[15]=(float)value/100;	}}
	public class MyChangeConcept16 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = concept_slider[16].getValue(); String str = Integer.toString(value);
	concept_label[16].setText("  "+concept[16]+"   "+str+" %");concept_preference[16]=(float)value/100;	}}
	public class MyChangeConcept17 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = concept_slider[17].getValue(); String str = Integer.toString(value);
	concept_label[17].setText("  "+concept[17]+"   "+str+" %");concept_preference[17]=(float)value/100;	}}
	public class MyChangeConcept18 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = concept_slider[18].getValue(); String str = Integer.toString(value);
	concept_label[18].setText("  "+concept[18]+"   "+str+" %");concept_preference[18]=(float)value/100;	}}
	
	public class MyChangeRatio1 implements ChangeListener{public void stateChanged(ChangeEvent ce){int value = skimming_ratio_slider.getValue(); String str = Integer.toString(value);
	skimming_ratio_radiobutton.setText("Skimming Ratio  "+str+" %");skimming_ratio=(float)value/100;	}}
	
	}
	
	class Computer
	{
	String movie_name;
	String movie_URL;
	int number_of_shots, summary_time,number_of_scenes;
	int[][] shot_info;
	Vector<Integer>[] scene_info;
	float[][] actor_score;
	float[][] event_score;
	float[][] concept_score;
	Vector<String>[] transcript_info;
	float[][] shot_score;
	float[][] scene_score;
	double[][] segment_address;
	
	//	Read shot information from shot csv file
	void load_shot_files(String movie_name)
	{
	String shot_csvfile="E:/Movies/"+movie_name+"/shot.csv";
	movie_URL="E:/Movies/"+movie_name+"/"+movie_name+".mpg";
	try
	{
    BufferedReader br = new BufferedReader(new FileReader(shot_csvfile));
    StringTokenizer st = null;
	String temp="";
	temp=br.readLine();
	number_of_shots=Integer.parseInt(temp);
	shot_info=new int[number_of_shots][4];
	for(int i=0;i<number_of_shots;i++)
	{
	temp = br.readLine();
    st = new StringTokenizer(temp, ",");
	for(int j=0;j<4;j++)
		{
		shot_info[i][j]=Integer.parseInt(st.nextToken());
		}
	}
	}
	catch(Exception e)
	{
	System.out.println(e);
	}
	}
	
	//	Read scene information from scene csv file

	void load_scene_files(String movie_name)
	{
	String scene_csvfile="E:/Movies/"+movie_name+"/Scene.csv";
	try
	{
    BufferedReader br = new BufferedReader(new FileReader(scene_csvfile));
    StringTokenizer st = null;
	String temp="";
	temp=br.readLine();
	number_of_scenes=Integer.parseInt(temp);
	scene_info= (Vector<Integer>[]) new Vector[number_of_scenes];
	for(int i = 0; i<number_of_scenes; i++)
	scene_info[i] = new Vector<Integer>();
	for(int i=0;i<number_of_scenes;i++)
	{
	temp = br.readLine();
    st = new StringTokenizer(temp, ",");
	while(st.hasMoreTokens())
	{
	scene_info[i].add(Integer.parseInt(st.nextToken()));
	}
	}
	}
	catch(Exception e)
	{
	System.out.println(e);
	}
	}
	
	//	Read actor information from actor csv file
	void load_actor_files(String movie_name)
	{
	String actor_csvfile="E:/Movies/"+movie_name+"/actor.csv";
	try
	{
    BufferedReader br = new BufferedReader(new FileReader(actor_csvfile));
    StringTokenizer st = null;
	String temp="";
	actor_score=new float[number_of_shots][4];
	temp = br.readLine();
	for(int i=0;i<number_of_shots;i++)
	{
	temp = br.readLine();
    st = new StringTokenizer(temp, ",");
	for(int j=0;j<4;j++)
		{
		actor_score[i][j]=Float.parseFloat(st.nextToken());
		}
	}
	}
	catch(Exception e)
	{
	System.out.println(e);
	}
	}
	
	//	Read event information from event csv file
	void load_event_files(String movie_name)
	{
	String event_csvfile="E:/Movies/"+movie_name+"/event.csv";
	try
	{
    BufferedReader br = new BufferedReader(new FileReader(event_csvfile));
    StringTokenizer st = null;
	String temp="";
	event_score=new float[number_of_shots][6];
	for(int i=0;i<number_of_shots;i++)
	{
	temp = br.readLine();
    st = new StringTokenizer(temp, ",");
	for(int j=0;j<6;j++)
		{
		event_score[i][j]=Float.parseFloat(st.nextToken());
		if(event_score[i][j]<0)
		{
		event_score[i][j]=0;
		}
		}
	}
	}
	catch(Exception e)
	{
	System.out.println(e);
	}
	}
	
	//	Read concept information from concept csv file
	void load_concept_files(String movie_name)
	{
	String concept_csvfile="E:/Movies/"+movie_name+"/concept.csv";
	try
	{
    BufferedReader br = new BufferedReader(new FileReader(concept_csvfile));
    StringTokenizer st = null;
	String temp="";
	concept_score=new float[number_of_shots][19];
	for(int i=0;i<number_of_shots;i++)
	{
	temp = br.readLine();
    st = new StringTokenizer(temp, ",");
	for(int j=0;j<19;j++)
		{
		concept_score[i][j]=Float.parseFloat(st.nextToken());		
		if(concept_score[i][j]<0)
		{
		concept_score[i][j]=0;
		}
		}
	}
	}
	catch(Exception e)
	{
	System.out.println(e);
	}
	}

	//	Read transcript information from transcript csv file
	
	void load_transcript_files(String movie_name)
	{
	String transcript_csvfile="E:/Movies/"+movie_name+"/Transcript.csv";
	try
	{
    BufferedReader br = new BufferedReader(new FileReader(transcript_csvfile));
    StringTokenizer st = null;
	String temp="";
	transcript_info= (Vector<String>[]) new Vector[number_of_shots];
	for(int i = 0; i<number_of_shots; i++)
	transcript_info[i] = new Vector<String>();
	for(int i=0;i<number_of_shots;i++)
	{
	temp = br.readLine();
    st = new StringTokenizer(temp, ",");
	while(st.hasMoreTokens())
	{
	transcript_info[i].add(st.nextToken());
	}
	}
	}
	catch(Exception e)
	{
	System.out.println(e);
	}
	}
	
	void matcher(int summary_time, float[] actor_preference, float[] event_preference, float[] concept_preference, String keywords_preference, int summary_element_flag, int summary_order_flag)
	{
	//	Match the preference and shots
	float actor_weight=0.4f;
	float event_weight=0.25f;
	float concept_weight=0.25f;
	float keyword_weight=0.1f;
	shot_score=new float[number_of_shots][2];
	scene_score=new float[number_of_scenes][2];
	Vector<String> keywords=new Vector<String>();
	float temp1=0.0f;
	float temp2=0.0f; 
	float temp3=0.0f;
	float temp4=0.0f;
	int under,upper;
	
	//	Tokenizing keywords preferences 
	try
	{
    StringTokenizer st = null;
	st = new StringTokenizer(keywords_preference, " ");
	while(st.hasMoreTokens())
	{
	keywords.add(st.nextToken());
	}
	}
	catch(Exception e)
	{
	System.out.println(e);
	}
	
	//	calculating similarity score for each shot
	for(int i=0;i<number_of_shots;i++)
	{
	shot_score[i][0]=i;
	temp1=temp2=temp3=temp4=0.0f;
	for(int j=0;j<4;j++)
	{
	temp1=temp1+(actor_weight*actor_score[i][j]*actor_preference[j]);
	}
	for(int j=0;j<6;j++)
	{
	temp2=temp2+(event_weight*event_score[i][j]*event_preference[j]);
	}
	for(int j=0;j<19;j++)
	{
	temp3=temp3+(concept_weight*concept_score[i][j]*concept_preference[j]);
	}
	
	under=keywords.size()+transcript_info[i].size();
	upper=0;
	for(int j=0;j<keywords.size();j++)
	{
	for(int k=0;k<transcript_info[i].size();k++)
	{
	if(keywords.get(j).equalsIgnoreCase(transcript_info[i].get(k)))
	{
	upper=upper+1;
	}
	}
	}
	temp4=keyword_weight*upper/under;
	shot_score[i][1]=temp1+temp2+temp3+temp4;
	}
	
	Vector<String> segment_show=new Vector<String>();
	int time=0;
	int i=0;
	//	Show segments as shots
	if(summary_element_flag==0)
	{
	//	Sort in decreasing order
	java.util.Arrays.sort(shot_score, new java.util.Comparator<float[]>(){
			public int compare(float[] o1, float[] o2) {if(o1[1]>o2[1]) return -1;
			else if(o1[1]<o2[1]) return 1;else return 0;}});
			
	//	Select the shots
	do
	{
	if(shot_score[i][1]==0)
	break;
	segment_show.addElement(String.valueOf((int)(shot_score[i][0])));
	time=time+shot_info[Integer.parseInt(String.valueOf((int)shot_score[i][0]))][3];
	i++;
	}
	while(time<summary_time);
	
	segment_address=new double[segment_show.size()][3];
	for(i=0;i<segment_show.size();i++)
	{
		segment_address[i][0] =Integer.parseInt(segment_show.get(i));
		segment_address[i][1] = Double.parseDouble(String.valueOf(shot_info[Integer.parseInt(segment_show.get(i))][1]));
		segment_address[i][2] = Double.parseDouble(String.valueOf(shot_info[Integer.parseInt(segment_show.get(i))][2]));
	}
	//	Order shots by time
	if(summary_order_flag==1)
	{
	java.util.Arrays.sort(segment_address, new java.util.Comparator<double[]>(){
			public int compare(double[] o1, double[] o2) {if(o1[1]<o2[1]) return -1;
			else if(o1[1]>o2[1]) return 1;else return 0;}});
	}
	
	}
	//	Show segments as scenes
	else if(summary_element_flag==1)
	{
	for(i=0;i<number_of_scenes;i++)
	{
	scene_score[i][0]=i;
	scene_score[i][1]=0;
	for(int j=0;j<scene_info[i].size();j++)
	{
	scene_score[i][1]=scene_score[i][1]+shot_score[scene_info[i].get(j)][1];
	scene_score[i][1]=scene_score[i][1]/scene_info[i].size();
	}
	
	}
	//	Sort in decreasing order
	java.util.Arrays.sort(scene_score, new java.util.Comparator<float[]>(){
			public int compare(float[] o1, float[] o2) {if(o1[1]>o2[1]) return -1;
			else if(o1[1]<o2[1]) return 1;else return 0;}});
	
	//	Select the scenes
	int scs=0;
	int sce=0;
	i=0;
	
	do
	{
	if(scene_score[i][1]<=0)
	break;		
	segment_show.addElement(String.valueOf((int)(scene_score[i][0])));
	scs=shot_info[scene_info[Integer.parseInt(String.valueOf((int)scene_score[i][0]))].get(0)][1];
	sce=shot_info[scene_info[Integer.parseInt(String.valueOf((int)scene_score[i][0]))].get(scene_info[Integer.parseInt(String.valueOf((int)scene_score[i][0]))].size()-1)][2];
	time=time+(sce-scs);
	i++;
	}
	while(time<summary_time);
	
	segment_address=new double[segment_show.size()][3];
	
	for(i=0;i<segment_show.size();i++)
	{
		segment_address[i][0] = Integer.parseInt(segment_show.get(i));
		segment_address[i][1] = Double.parseDouble(String.valueOf(shot_info[scene_info[Integer.parseInt(String.valueOf((int)scene_score[i][0]))].get(0)][1]));
		segment_address[i][2] = Double.parseDouble(String.valueOf(shot_info[scene_info[Integer.parseInt(String.valueOf((int)scene_score[i][0]))].get(scene_info[Integer.parseInt(String.valueOf((int)scene_score[i][0]))].size()-1)][2]));
	}
	//	Order scenes by time
	if(summary_order_flag==1)
	{
	java.util.Arrays.sort(segment_address, new java.util.Comparator<double[]>(){
			public int compare(double[] o1, double[] o2) {if(o1[1]<o2[1]) return -1;
			else if(o1[1]>o2[1]) return 1;else return 0;}});
	}
	}
	//	Show which segments are now playing
	for(i=0;i<segment_show.size();i++)
	{
		System.out.println("Segment ID : "+segment_address[i][0]+"     Start : "+segment_address[i][1]+"     End : "+segment_address[i][2]);
	}
	}
	
	}
	
	//	Video Player
	class MediaPanel extends JPanel implements ControllerListener
	{
	Player mediaPlayer;
	double buf[][];
	int looper;
	int nos;
   void play_video( URL mediaURL, double segment_address[][] )
   {
	FramePositioningControl fpc;   
	buf=new double[segment_address.length][3];
	
	setLayout( new BorderLayout() ); 
      
      Manager.setHint( Manager.LIGHTWEIGHT_RENDERER, true );
      
      try
      {
         mediaPlayer = Manager.createRealizedPlayer( mediaURL );
         
         Component video = mediaPlayer.getVisualComponent();
         Component controls = mediaPlayer.getControlPanelComponent();
         
         if ( video != null ) 
            add( video, BorderLayout.CENTER ); 

         if ( controls != null ) 
            add( controls, BorderLayout.SOUTH ); 
			
			buf=segment_address;
			nos=buf.length;
			
			mediaPlayer.realize();
			mediaPlayer.prefetch();
		 
			mediaPlayer.setMediaTime(new Time(buf[0][1]));
			
			mediaPlayer.prefetch();
			mediaPlayer.addControllerListener(this);
			looper=0;
			mediaPlayer.start();
      } 
      catch ( NoPlayerException noPlayerException )
      {
         System.err.println( "No media player found" );
      } 
      catch ( CannotRealizeException cannotRealizeException )
      {
         System.err.println( "Could not realize media player" );
      } 
      catch ( IOException iOException )
      {
         System.err.println( "Error reading from the source" );
      } 
   } 
   public void controllerUpdate(ControllerEvent evt) 
	{	
	try
	{
	if (evt instanceof StartEvent) 
	{
	Thread.sleep((int)(buf[looper][2]-buf[looper][1])*1000);
	mediaPlayer.stop();
	looper=looper+1;
	mediaPlayer.setMediaTime(new Time(buf[looper][1])); 
	if(looper>=nos)
	{
	mediaPlayer.stop();
	}
	}
	else if (evt instanceof MediaTimeSetEvent) 
	{
		mediaPlayer.start();
	}
	}
	catch(InterruptedException ie)
	  {
	  System.err.println( "Thread Interrupted" );
	  }
	  }
	} 