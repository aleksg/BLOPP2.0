package no.blopp.app.med.div;

public enum Actions
{
	action1s("http://folk.ntnu.no/yngvesva/blopp/res/1s_notification.mp3"),
	action2s("http://folk.ntnu.no/yngvesva/blopp/res/2s_fetch_adult_instructions.mp3"),
	action3s("http://folk.ntnu.no/yngvesva/blopp/res/3s_two_different_medicines_instructions.mp3"),
	action35s("http://folk.ntnu.no/yngvesva/blopp/res/capp_3.5s.mp3"),
	action41s("http://folk.ntnu.no/yngvesva/blopp/res/capp_4.1s.mp3"),
	action5s("http://folk.ntnu.no/yngvesva/blopp/res/5s_first_done_instructions.mp3"),
	action6s("http://folk.ntnu.no/yngvesva/blopp/res/6s_postmed_compliment.mp3"),
	action7s1("http://folk.ntnu.no/yngvesva/blopp/res/7s1_reward_one.mp3"),
	action7s2("http://folk.ntnu.no/yngvesva/blopp/res/7s2_reward_two.mp3"),
	action7s3("http://folk.ntnu.no/yngvesva/blopp/res/7s3_reward_three.mp3"),
	action7s4("http://folk.ntnu.no/yngvesva/blopp/res/7s4_reward_four.mp3"),
	action7s5("http://folk.ntnu.no/yngvesva/blopp/res/7s5_reward_five.mp3"),
	action7s7("http://folk.ntnu.no/yngvesva/blopp/res/7s7_reward_seven.mp3"),
	action7s10("http://folk.ntnu.no/yngvesva/blopp/res/7s10_reward_ten.mp3"),
	action8s("http://folk.ntnu.no/yngvesva/blopp/res/8s_get_second_instructions.mp3"),
	action9s("http://folk.ntnu.no/yngvesva/blopp/res/9s_postmed_nanoz_instructions.mp3"),
	action1b("http://folk.ntnu.no/yngvesva/blopp/res/1b_blue_instructions_one.mp3"),
	action2b("http://folk.ntnu.no/yngvesva/blopp/res/2b_blue_instructions_two.mp3"),
	action3b1("http://folk.ntnu.no/yngvesva/blopp/res/3b1_blue_instructions_three.mp3"),
	action3b2("http://folk.ntnu.no/yngvesva/blopp/res/3b2_blue_instructions_three_twice.mp3"),
	action1o("http://folk.ntnu.no/yngvesva/blopp/res/1o_orange_instructions_one.mp3"),
	action2o("http://folk.ntnu.no/yngvesva/blopp/res/2o_orange_instructions_two.mp3"),
	action3o1("http://folk.ntnu.no/yngvesva/blopp/res/3o1_orange_instructions_three.mp3"),
	action3o2("http://folk.ntnu.no/yngvesva/blopp/res/3o2_orange_instructions_three_twice.mp3"),
	action1p("http://folk.ntnu.no/yngvesva/blopp/res/1p_purple_instructions_one.mp3"),
	action2p("http://folk.ntnu.no/yngvesva/blopp/res/2p_purple_instructions_two.mp3"),
	action3p1("http://folk.ntnu.no/yngvesva/blopp/res/3p1_purple_instructions_three.mp3"),
	action3p2("http://folk.ntnu.no/yngvesva/blopp/res/3p2_purple_instructions_three_twice.mp3");
	
	private final String url;
	
	private Actions(String url) {
		this.url = url;
	}
	
	public String getSoundFileUrl() {
		return url;
	}
}
