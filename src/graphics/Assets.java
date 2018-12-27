package graphics;

import java.awt.image.BufferedImage;

public class Assets {   //to Load all resources once
	public static BufferedImage p1_armoredNos,p2_armoredNos,controls,bg1,canbg,bg,p1_super,p1_armored,p1_tech,p1_medic,p1_ranged,p1_speedster,p1_sidekick,p2_super,p2_armored,p2_tech,p2_medic,p2_ranged,p2_speedster,p2_sidekick;
	public static void init() {
		p1_tech = ImageLoader.loadImage("/textures/atechPlayer1.png");
		p2_tech = ImageLoader.loadImage("/textures/atechPlayer2.png");
		p1_super = ImageLoader.loadImage("/textures/asuperPlayer1.png");
		p2_super = ImageLoader.loadImage("/textures/asuperPlayer2.png");
		p1_ranged = ImageLoader.loadImage("/textures/arangedPlayer1.png");
		p2_ranged = ImageLoader.loadImage("/textures/arangedPlayer2.png");
		p1_medic = ImageLoader.loadImage("/textures/amedicPlayer1.png");
		p2_medic = ImageLoader.loadImage("/textures/amedicPlayer2.png");
		p1_speedster = ImageLoader.loadImage("/textures/aspeedsterPlayer1.png");
		p2_speedster = ImageLoader.loadImage("/textures/aspeedsterPlayer2.png");
		p1_armored = ImageLoader.loadImage("/textures/aarmoredPlayer1.png");
		p2_armored = ImageLoader.loadImage("/textures/aarmoredPlayer2.png");
		p1_armoredNos = ImageLoader.loadImage("/textures/aarmoredPlayer1Nos.png");
		p2_armoredNos = ImageLoader.loadImage("/textures/aarmoredPlayer2Nos.png");
		p1_sidekick = ImageLoader.loadImage("/textures/asidekickPlayer1.png");
		p2_sidekick = ImageLoader.loadImage("/textures/asidekickPlayer2.png");
		controls = ImageLoader.loadImage("/textures/controls.jpg");
		bg = ImageLoader.loadImage("/textures/bg.jpg");
		canbg = ImageLoader.loadImage("/textures/canbg.jpg");
		bg1 = ImageLoader.loadImage("/textures/bg1.png");

	}

}
