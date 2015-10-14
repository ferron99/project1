// Project 1, create a simple game were a hero chases gold and a monster chases the hero
// Nick Ferro
// CST 112  10-14-2015

int state;               //toggle state for sun and moon
float sx,sy;             //sun position
float mx,my;             //moon position
float goldX, goldY;      //gold position
float manX, manY;        //hero's position
float eyeX, eyeY;    //monster's position
float dark;              //mask for night
float horizon;
int frame;               //frame count for hero's run animation
int score;
int mode;                // gamemode or start screen
int eyeKill;             //trigger var for getting rid of monster in win state

void setup() {
  size( 640,480);
  horizon=  height/4;
  sx = -50;
  mx = -50;
  goldX = random(width/4, width*3/4);
  goldY = random(height/3.2, height*3/4);
  manX= 260;
  manY= 70;
  eyeX= 0;
  eyeY= random(height/4,height);
  state = 0;
  dark = 110;
  frame = 0;
  score = 0;
  mode = 0;
  eyeKill = 0;
  reset();
}

void draw() {
  
   if (mode == 0){
   startScreen();
  }
  if (mode == 1){
   gameMode();
  } 
}

void reset(){
  goldX = random(width/4, width*3/4);
  goldY = random(height/3.2, height*3/4);
  eyeX= 0;
  eyeY= random(height/4,height);
  manX= 260;
  manY= 70;
}

void startScreen(){
  fill(0);
  textSize(32);
  text("Project 1 Game",(width/2)-100,(height/2)-100);
  textSize(14);
  text("Help the man get the gold. Prevent the monster from catching the man.",(width/2)-200,(height/2)-50);
  text("If the man gets the gold, you earn 50 points. If the monster catches the",(width/2)-200,(height/2)-25);
  text("man, you lose 100 points. Hitting the reset button puts the man in the",(width/2)-200,(height/2));
  text("house at the cost of 50 points.",(width/2)-200,(height/2)+25);
  text("Hit the button below to start.",(width/2)-200,(height/2)+50);
  fill(0);
  strokeWeight(4);
  stroke(255);
  rect((width/2)-50,(height/2)+100, 83,30);
  fill(255);
  text("Start", (width/2)-40,(height/2)+120);
}

void gameMode(){
   drawMiscScenery();
   if (state == 0){
    drawSun();
  }
  if (state == 1){
    drawMoon();
  }
  drawTrees();
  drawGold(goldX,goldY);
  heroStuff();
  if (eyeKill == 0){
  monsterStuff();
  }
  drawHouse(250,50);
  drawNight();
  interactions();
  displayScore();
  button();
}

void drawSun(){
  sy = -sqrt(pow(1000,2)-pow(sx-320, 2))+1000;
  sx= sx+1;
  fill( 255,255,0 );
  ellipse( sx, sy, 40,40 );  
  if (sx > width+50){
    state = 1;
    sx = -50;
  }
   if (sx >-20 && sx<80){
    dark = dark -1;
  }
  if (sx >560 && sx<660){
    dark = dark +1;
  }
}

void drawMoon(){
  my = -sqrt(pow(1000,2)-pow(mx-320, 2))+1000;
  mx= mx+1.5;
  fill( 255,255,255 );
  ellipse( mx, my, 40,40 );  
  if (mx > width+50){
    state = 0;
    mx = -50;
  }
}

void drawMiscScenery(){
  noStroke();
  background( 100,150,200 );                // sky
  fill( 100,200,100 );
  rect( 0,horizon, width,height*3/4 );          // grass.
}
void drawTrees(){
  noStroke();
  drawTree(65,152);
  drawTree(160,145);
  drawTree(480,150);
}

void drawTree(float treeX, float treeY){
  fill(139,69,19);
  rect(treeX-5, treeY-50, 10, 40);
  fill(34,139,34);
  quad(treeX-50, treeY-50, treeX-25, treeY-80, treeX+25, treeY-80, treeX+50, treeY-50);
  quad(treeX-40, treeY-80, treeX-15, treeY-110, treeX+15, treeY-110, treeX+40, treeY-80);
  triangle(treeX-30, treeY-110, treeX+30, treeY-110, treeX, treeY-150);
}

void  drawHouse(float x, float y){
  noStroke();
  fill(175);                                        
  rect(x,y,150,100);
  fill(150);
  triangle(x,y,x+150,y,x+75,y-30);
  fill(0);
  rect(x+65,y+60,20,40);
  fill(255,255,0);
  rect(x+30,y+25, 20,20);
  rect(x+100,y+25, 20,20);
}

void drawGold(float x, float y){
  fill(255,215,0);
  ellipse(x, y, 10,10);
}

void heroStuff(){
  runningMan();
  float dx = goldX - manX;
  manX += dx * 0.05;
  float dy = goldY - manY;
  manY += dy * 0.05;
}

void runningMan(){
  frame = frame + 1;
  if(frame/15 % 8 == 0){
    drawFrame1();
  }else if(frame/15 % 8 == 1){
    drawFrame2();
  }else if(frame/15 % 8 == 2){
    drawFrame3();
  }else if(frame/15 % 8 == 3){
    drawFrame4();
  }else if(frame/15 % 8 == 4){
    drawFrame5();
  }else if(frame/15 % 8 == 5){
    drawFrame6();
  }else if(frame/15 % 8 == 6){
    drawFrame7();
  }else if(frame/15 % 8 == 7){
    drawFrame8();
  }
}

void drawFrame1(){
  if (manX < goldX) {
 noStroke();
 fill(233,203,141);              //skin color
 rect(manX-5,manY-40,25,30);           //head
 fill(0,191,243);                //dark shirt color
 quad(manX+10,manY-8, manX+18,manY-10, manX+25,manY-5, manX+25,manY);  //front arm
 fill(109,207,246);              //light shirt color
 rect(manX-10,manY-10,25,25);          //bodmanY
 rect(manX-25,manY-15,20,5);           //back upper arm
 fill(233,203,141);              //skin color
 rect(manX-35,manY-5,10,10);           //back hand
 fill(109,207,246);              //light shirt color
 quad(manX-25,manY-15, manX-20,manY-10, manX-25,manY, manX-30,manY-5); //backforearm
 fill(233,203,141);              //skin color
 rect(manX+20,manY-5,10,10);           //front hand
 fill(246,150,121);              //light pants color
 rect(manX-10,manY+15,25,10);          //waist
 quad(manX+15,manY+22, manX+12,manY+25, manX+35,manY+50, manX+40,manY+50); //front leg
 fill(242,108,79);               //dark pants color
 quad(manX-10,manY+25, manX-5,manY+25, manX-15,manY+40, manX-20,manY+35);  //back upper leg
 rect(manX-35,manY+35, 20,5);          //back shin
 fill(54,47,45);                 //shoe hair color
 rect(manX-40,manY+35,5,15);           //back shoe
 quad(manX+35,manY+50, manX+38,manY+55,  manX+51,manY+49, manX+48,manY+45); //front shoe
 rect(manX-5,manY-40, 25, 5);          //hair top bar
 rect(manX-5,manY-35, 10,5);           //hair mid
 rect(manX-5,manY-30, 5,5);            //hair low
  }else{
 noStroke();
 fill(233,203,141);              //skin color
 rect(manX-15,manY-40,25,30);           //head
 fill(0,191,243);                //dark shirt color
 quad(manX-10,manY-8, manX-18,manY-10, manX-25,manY-5, manX-25,manY);  //front arm
 fill(109,207,246);              //light shirt color
 rect(manX-10,manY-10,25,25);          //bodmanY
 rect(manX+10,manY-15,15,5);           //back upper arm
 fill(233,203,141);              //skin color
 rect(manX-35,manY-5,10,10);           //back hand
 fill(109,207,246);              //light shirt color
 quad(manX+25,manY-15, manX+20,manY-10, manX+25,manY, manX+30,manY-5); //backforearm
 fill(233,203,141);              //skin color
 rect(manX+20,manY-5,10,10);           //front hand
 fill(246,150,121);              //light pants color
 rect(manX-10,manY+15,25,10);          //waist
 quad(manX-10,manY+22, manX-8,manY+25, manX-30,manY+50, manX-35,manY+50); //front leg
 fill(242,108,79);               //dark pants color
 quad(manX+15,manY+25, manX+10,manY+25, manX+20,manY+40, manX+25,manY+35);  //back upper leg
 rect(manX+20,manY+35, 20,5);          //back shin
 fill(54,47,45);                 //shoe hair color
 rect(manX+40,manY+35,5,15);           //back shoe
 quad(manX-30,manY+50, manX-32,manY+55,  manX-46,manY+49, manX-42,manY+45); //front shoe
 rect(manX-15,manY-40, 25, 5);          //hair top bar
 rect(manX,manY-35, 10,5);           //hair mid
 rect(manX+5,manY-30, 5,5);            //hair low
}
  
}

void drawFrame2(){
if (manX < goldX) {
 noStroke();
 fill(233,203,141);              //skin color
 rect(manX-5,manY-35,25,30);           //head
 fill(0,191,243);                //dark shirt color
 quad(manX+10,manY-3, manX+18,manY-5, manX+25,manY, manX+25,manY+5);  //front arm
 fill(109,207,246);              //light shirt color
 rect(manX-10,manY-5,25,25);          //bodmanY
 rect(manX-25,manY-10,20,5);           //back upper arm
 fill(233,203,141);              //skin color
 rect(manX-35,manY,10,10);           //back hand
 fill(109,207,246);              //light shirt color
 quad(manX-25,manY-10, manX-20,manY-5, manX-25,manY+5, manX-30,manY); //backforearm
 fill(233,203,141);              //skin color
 rect(manX+20,manY,10,10);           //front hand
 fill(246,150,121);              //light pants color
 rect(manX-10,manY+20,25,10);          //waist
 quad(manX+15,manY+27, manX+12,manY+30, manX+20,manY+35, manX+25,manY+35); //front leg upper
 rect(manX+20,manY+35,5,20);           //front leg shin
 fill(242,108,79);               //dark pants color
 quad(manX-10,manY+30, manX-5,manY+30, manX-15,manY+45, manX-20,manY+40);  //back upper leg
 rect(manX-30,manY+40, 15,5);          //back shin
 fill(54,47,45);                 //shoe hair color
 rect(manX-35,manY+40,5,15);           //back shoe
 rect(manX+20,manY+55,15,5);           //front shoe
 rect(manX-5,manY-35, 25, 5);          //hair top bar
 rect(manX-5,manY-30, 10,5);           //hair mid
 rect(manX-5,manY-25, 5,5);            //hair low
}else{
  noStroke();
 fill(233,203,141);              //skin color
 rect(manX-15,manY-35,25,30);           //head
 fill(0,191,243);                //dark shirt color
 quad(manX-10,manY-3, manX-18,manY-5, manX-25,manY, manX-25,manY+5);  //front arm
 fill(109,207,246);              //light shirt color
 rect(manX-10,manY-5,25,25);          //bodmanY
 rect(manX+10,manY-10,15,5);           //back upper arm
 fill(233,203,141);              //skin color
 rect(manX-35,manY,10,10);           //back hand
 fill(109,207,246);              //light shirt color
 quad(manX+25,manY-10, manX+20,manY-5, manX+25,manY+5, manX+30,manY); //backforearm
 fill(233,203,141);              //skin color
 rect(manX+20,manY,10,10);           //front hand
 fill(246,150,121);              //light pants color
 rect(manX-10,manY+20,25,10);          //waist
 quad(manX-10,manY+27, manX-8,manY+30, manX-15,manY+35, manX-20,manY+35); //front leg upper
 rect(manX-20,manY+35,5,20);           //front leg shin
 fill(242,108,79);               //dark pants color
 quad(manX+15,manY+30, manX+10,manY+30, manX+20,manY+45, manX+25,manY+40);  //back upper leg
 rect(manX+20,manY+40, 15,5);          //back shin
 fill(54,47,45);                 //shoe hair color
 rect(manX+35,manY+40,5,15);           //back shoe
 rect(manX-30,manY+55,15,5);           //front shoe
 rect(manX-15,manY-35, 25, 5);          //hair top bar
 rect(manX,manY-30, 10,5);           //hair mid
 rect(manX+5,manY-25, 5,5);            //hair low
}
  
  
}

void drawFrame3(){
  if (manX < goldX) {
 noStroke();
 fill(233,203,141);              //skin color
 rect(manX-5,manY-30,25,30);           //head
 fill(0,191,243);                //dark shirt color
 rect(manX+15,manY,5,20);              //front arm
 fill(109,207,246);              //light shirt color
 rect(manX-10,manY,25,25);             //bodmanY
 rect(manX-20,manY-5,15,10);           //back upper arm
 fill(233,203,141);              //skin color
 rect(manX-20,manY+10,10,10);          //back hand
 fill(109,207,246);              //light shirt color
 rect(manX-20,manY+5,5,5);             //backforearm
 fill(233,203,141);              //skin color
 rect(manX+20,manY+10,10,10);          //front hand
 fill(246,150,121);              //light pants color
 rect(manX-10,manY+25,25,10);          //waist
 quad(manX+15,manY+32, manX+12,manY+35, manX+20,manY+40, manX+25,manY+40);           //front leg upper
 quad(manX+20,manY+40, manX+25,manY+40, manX+14,manY+59, manX+10,manY+55);           //front leg shin
 fill(242,108,79);               //dark pants color
 quad(manX,manY+35, manX-5,manY+35, manX+3,manY+40, manX+10,manY+40);             //back upper leg
 quad(manX+5,manY+40, manX+10,manY+40, manX-1,manY+59, manX-5,manY+55);        //back shin
 fill(54,47,45);                 //shoe hair color
 quad(manX-5,manY+55, manX,manY+55, manX+5,manY+65, manX,manY+65);           //back shoe
 quad(manX+10,manY+55, manX+15,manY+55, manX+20,manY+65, manX+15,manY+65);           //front shoe
 rect(manX-5,manY-30, 25, 5);          //hair top bar
 rect(manX-5,manY-25, 15,5);           //hair mid
 rect(manX-5,manY-20, 10,5);           //hair low
  }else{
  noStroke();
 fill(233,203,141);              //skin color
 rect(manX-15,manY-30,25,30);           //head
 fill(0,191,243);                //dark shirt color
 rect(manX-15,manY,5,20);              //front arm
 fill(109,207,246);              //light shirt color
 rect(manX-10,manY,25,25);             //bodmanY
 rect(manX+10,manY-5,15,10);           //back upper arm
 fill(233,203,141);              //skin color
 rect(manX+15,manY+10,10,10);          //back hand
 fill(109,207,246);              //light shirt color
 rect(manX+20,manY+5,5,5);             //backforearm
 fill(233,203,141);              //skin color
 rect(manX-25,manY+10,10,10);          //front hand
 fill(246,150,121);              //light pants color
 rect(manX-10,manY+25,25,10);          //waist
 quad(manX-10,manY+32, manX-7,manY+35, manX-15,manY+40, manX-20,manY+40);           //front leg upper
 quad(manX-15,manY+40, manX-20,manY+40, manX-9,manY+59, manX-5,manY+55);           //front leg shin
 fill(242,108,79);               //dark pants color
 quad(manX+5,manY+35, manX+10,manY+35, manX+2,manY+40, manX-5,manY+40);             //back upper leg
 quad(manX,manY+40, manX-5,manY+40, manX+6,manY+59, manX+10,manY+55);        //back shin
 fill(54,47,45);                 //shoe hair color
 quad(manX+10,manY+55, manX+5,manY+55, manX,manY+65, manX+5,manY+65);           //back shoe
 quad(manX-5,manY+55, manX-10,manY+55, manX-15,manY+65, manX-10,manY+65);           //front shoe
 rect(manX-15,manY-30, 25, 5);          //hair top bar
 rect(manX-5,manY-25, 15,5);           //hair mid
 rect(manX,manY-20, 10,5);           //hair low
 
}
}

void drawFrame4(){
 if (manX < goldX) {
  noStroke();
 fill(233,203,141);              //skin color
 rect(manX-5,manY-35,25,30);           //head
 fill(0,191,243);               //dark shirt color
 rect(manX-25,manY-10,20,5);           //back arm upper
 quad(manX-25,manY-10, manX-20,manY-5, manX-25,manY+5, manX-30,manY); //backforearm
 fill(109,207,246);              //light shirt color
 quad(manX+10,manY-3, manX+18,manY-5, manX+25,manY, manX+25,manY+5);  //front arm
 rect(manX-10,manY-5,25,25);           //bodmanY
 fill(233,203,141);              //skin color
 rect(manX-35,manY,10,10);             //back hand
 fill(109,207,246);              //light shirt color
 fill(233,203,141);              //skin color
 rect(manX+20,manY,10,10);             //front hand
 fill(246,150,121);              //light pants color
 rect(manX-10,manY+20,25,10);          //waist
 quad(manX-10,manY+30, manX-5,manY+30, manX-15,manY+40, manX-20,manY+40);  //back upper leg
 rect(manX-30,manY+35, 15,5);         //back shin
 fill(242,108,79);               //dark pants color
 rect(manX+5,manY+20,10,5);            //waist front dark portion upper
 rect(manX,manY+25,15,5);              //waist front dark portion lower
 quad(manX+15,manY+27, manX+12,manY+30, manX+20,manY+35, manX+25,manY+35); //front leg upper
 quad(manX+20,manY+35, manX+25,manY+35, manX+14,manY+54, manX+10,manY+50);     //front leg shin
 fill(54,47,45);                 //shoe hair color
 rect(manX-35,manY+35,5,15);           //back shoe
 quad(manX+10,manY+50, manX+15,manY+50, manX+20,manY+60, manX+15,manY+60);   //front shoe
 rect(manX-5,manY-35, 25, 5);          //hair top bar
 rect(manX-5,manY-30, 20,5);           //hair mid
 rect(manX-5,manY-25, 15,5);            //hair low
  }else{
 noStroke();
 fill(233,203,141);              //skin color
 rect(manX-15,manY-35,25,30);           //head
 fill(0,191,243);               //dark shirt color
 rect(manX+10,manY-10,15,5);           //back arm upper
 quad(manX+25,manY-10, manX+20,manY-5, manX+25,manY+5, manX+30,manY); //backforearm
 fill(109,207,246);              //light shirt color
 quad(manX-10,manY-3, manX-18,manY-5, manX-25,manY, manX-25,manY+5);  //front arm
 rect(manX-10,manY-5,25,25);           //bodmanY
 fill(233,203,141);              //skin color
 rect(manX-35,manY,10,10);             //back hand
 fill(109,207,246);              //light shirt color
 fill(233,203,141);              //skin color
 rect(manX+20,manY,10,10);             //front hand
 fill(246,150,121);              //light pants color
 rect(manX-10,manY+20,25,10);          //waist
 quad(manX+15,manY+30, manX+10,manY+30, manX+20,manY+40, manX+25,manY+40);  //back upper leg
 rect(manX+20,manY+35, 15,5);         //back shin
 fill(242,108,79);               //dark pants color
 rect(manX-10,manY+20,10,5);            //waist front dark portion upper
 rect(manX-10,manY+25,15,5);              //waist front dark portion lower
 quad(manX-10,manY+27, manX-7,manY+30, manX-15,manY+35, manX-20,manY+35); //front leg upper
 quad(manX-15,manY+35, manX-20,manY+35, manX-9,manY+54, manX-5,manY+50);     //front leg shin
 fill(54,47,45);                 //shoe hair color
 rect(manX+30,manY+35,5,15);           //back shoe
 quad(manX-5,manY+50, manX-10,manY+50, manX-15,manY+60, manX-10,manY+60);   //front shoe
 rect(manX-15,manY-35, 25, 5);          //hair top bar
 rect(manX-10,manY-30, 20,5);           //hair mid
 rect(manX-5,manY-25, 15,5);            //hair low
}
}

void drawFrame5(){
  if (manX < goldX) {
 noStroke();
 fill(233,203,141);              //skin color
 rect(manX-5,manY-40,25,30);           //head
 fill(0,191,243);                //dark shirt color
 rect(manX-25,manY-15,20,5);           //back upper arm
 quad(manX-25,manY-15, manX-20,manY-10, manX-25,manY, manX-30,manY-5); //backforearm
  fill(233,203,141);              //skin color
 rect(manX+20,manY-5,10,10);           //front hand
 fill(109,207,246);              //light shirt color
 quad(manX+10,manY-8, manX+18,manY-10, manX+29,manY+5, manX+21,manY+5);  //front arm
 rect(manX-10,manY-10,25,25);          //bodmanY
 fill(233,203,141);              //skin color
 rect(manX-35,manY-5,10,10);           //back hand
 fill(246,150,121);              //light pants color
 rect(manX-10,manY+15,25,10);          //waist
 quad(manX-10,manY+25, manX-5,manY+25, manX-15,manY+40, manX-20,manY+35);  //back upper leg
 rect(manX-35,manY+35, 20,5);          //back shin
 fill(242,108,79);               //dark pants color
 rect(manX+5,manY+15,10,5);
 rect(manX,manY+20,15,5);
 quad(manX+15,manY+22, manX+12,manY+25, manX+35,manY+50, manX+40,manY+50); //front leg
 fill(54,47,45);                 //shoe hair color
 rect(manX-40,manY+35,5,15);           //back shoe
 quad(manX+35,manY+50, manX+38,manY+55,  manX+51,manY+49, manX+48,manY+45); //front shoe
 rect(manX-5,manY-40, 25, 5);          //hair top bar
 rect(manX-5,manY-35, 20,5);           //hair mid
 rect(manX-5,manY-30, 15,5);            //hair low 
    }else{
 noStroke();
 fill(233,203,141);              //skin color
 rect(manX-15,manY-40,25,30);           //head
 fill(0,191,243);                //dark shirt color
 rect(manX+10,manY-15,15,5);           //back upper arm
 quad(manX+25,manY-15, manX+20,manY-10, manX+25,manY, manX+30,manY-5); //backforearm
  fill(233,203,141);              //skin color
 rect(manX+20,manY-5,10,10);           //front hand
 fill(109,207,246);              //light shirt color
 quad(manX-10,manY-8, manX-18,manY-10, manX-29,manY+5, manX-21,manY+5);  //front arm
 rect(manX-10,manY-10,25,25);          //bodmanY
 fill(233,203,141);              //skin color
 rect(manX-35,manY-5,10,10);           //back hand
 fill(246,150,121);              //light pants color
 rect(manX-10,manY+15,25,10);          //waist
 quad(manX+15,manY+25, manX+10,manY+25, manX+20,manY+40, manX+25,manY+35);  //back upper leg
 rect(manX+20,manY+35, 20,5);          //back shin
 fill(242,108,79);               //dark pants color
 rect(manX-10,manY+15,10,5);            //dark waist portion upper
 rect(manX-10,manY+20,15,5);              //dark waist portion lower
 quad(manX-10,manY+22, manX-7,manY+25, manX-30,manY+50, manX-40,manY+50); //front leg
 fill(54,47,45);                 //shoe hair color
 rect(manX+40,manY+35,5,15);           //back shoe
 quad(manX-30,manY+50, manX-33,manY+55,  manX-46,manY+49, manX-43,manY+45); //front shoe
 rect(manX-15,manY-40, 25, 5);          //hair top bar
 rect(manX-10,manY-35, 20,5);           //hair mid
 rect(manX-5,manY-30, 15,5);            //hair low
}
}

void drawFrame6(){
 if (manX < goldX) {
 noStroke();
 fill(233,203,141);              //skin color
 rect(manX-5,manY-35,25,30);           //head
 fill(0,191,243);                //dark shirt color
 quad(manX-25,manY-10, manX-20,manY-5, manX-25,manY+5, manX-30,manY); //backforearm
 rect(manX-25,manY-10,20,5);           //back upper arm
 fill(233,203,141);              //skin color
 rect(manX+20,manY,10,10);           //front hand
 fill(109,207,246);              //light shirt color
 rect(manX-10,manY-5,25,25);          //bodmanY
 quad(manX+10,manY-3, manX+18,manY-5, manX+30,manY+10, manX+23,manY+10);  //front arm
 fill(233,203,141);              //skin color
 rect(manX-35,manY,10,10);           //back hand
 fill(246,150,121);              //light pants color
 quad(manX-10,manY+30, manX-5,manY+30, manX-15,manY+45, manX-20,manY+40);  //back upper leg
 rect(manX-30,manY+40, 15,5);          //back shin
 rect(manX-10,manY+20,25,10);          //waist
 fill(242,108,79);               //dark pants color
 rect(manX+5,manY+20,10,5);
 rect(manX,manY+25,15,5);
 quad(manX+15,manY+27, manX+12,manY+30, manX+20,manY+35, manX+25,manY+35); //front leg upper
 rect(manX+20,manY+35,5,20);           //front leg shin
 fill(54,47,45);                 //shoe hair color
 rect(manX-35,manY+40,5,15);           //back shoe
 rect(manX+20,manY+55,15,5);           //front shoe
 rect(manX-5,manY-35, 25, 5);          //hair top bar
 rect(manX-5,manY-30, 20,5);           //hair mid
 rect(manX-5,manY-25, 15,5);            //hair low
   }else{
     noStroke();
 fill(233,203,141);              //skin color
 rect(manX-15,manY-35,25,30);           //head
 fill(0,191,243);                //dark shirt color
 quad(manX+25,manY-10, manX+20,manY-5, manX+25,manY+5, manX+30,manY); //backforearm
 rect(manX+10,manY-10,15,5);           //back upper arm
 fill(233,203,141);              //skin color
 rect(manX+20,manY,10,10);           //front hand
 fill(109,207,246);              //light shirt color
 rect(manX-10,manY-5,25,25);          //bodmanY
 quad(manX-10,manY-3, manX-18,manY-5, manX-30,manY+10, manX-23,manY+10);  //front arm
 fill(233,203,141);              //skin color
 rect(manX-35,manY,10,10);           //back hand
 fill(246,150,121);              //light pants color
 quad(manX+15,manY+30, manX+10,manY+30, manX+20,manY+45, manX+25,manY+40);  //back upper leg
 rect(manX+20,manY+40, 15,5);          //back shin
 rect(manX-10,manY+20,25,10);          //waist
 fill(242,108,79);               //dark pants color
 rect(manX-10,manY+20,10,5);
 rect(manX-10,manY+25,15,5);
 quad(manX-10,manY+27, manX-7,manY+30, manX-15,manY+35, manX-20,manY+35); //front leg upper
 rect(manX-20,manY+35,5,20);           //front leg shin
 fill(54,47,45);                 //shoe hair color
 rect(manX+35,manY+40,5,15);           //back shoe
 rect(manX-30,manY+55,15,5);           //front shoe
 rect(manX-15,manY-35, 25, 5);          //hair top bar
 rect(manX-10,manY-30, 20,5);           //hair mid
 rect(manX-5,manY-25, 15,5);            //hair low
   }
}

void drawFrame7(){
if (manX < goldX) {
 noStroke();
 fill(233,203,141);              //skin color
 rect(manX-5,manY-30,25,30);           //head
 fill(0,191,243);                //dark shirt color
 rect(manX-20,manY+5,5,5);             //backforearm
 rect(manX-20,manY-5,15,10);           //back upper arm
 fill(109,207,246);              //light shirt color
 rect(manX+15,manY,5,20);              //front arm
 rect(manX-10,manY,25,25);             //bodmanY
 fill(233,203,141);              //skin color
 rect(manX-20,manY+10,10,10);          //back hand
 fill(109,207,246);              //light shirt color
 fill(233,203,141);              //skin color
 rect(manX+20,manY+10,10,10);          //front hand
 fill(246,150,121);              //light pants color
 rect(manX-10,manY+25,25,10);          //waist
 fill(242,108,79);               //dark pants color
 rect(manX+5,manY+25,10,5);
 rect(manX,manY+30,15,5);
 quad(manX+15,manY+32, manX+12,manY+35, manX+20,manY+40, manX+25,manY+40);           //front leg upper
 quad(manX+20,manY+40, manX+25,manY+40, manX+14,manY+59, manX+10,manY+55);           //front leg shin
 fill(246,150,121);              //light pants color
 quad(manX,manY+35, manX-5,manY+35, manX+3,manY+40, manX+10,manY+40);             //back upper leg
 quad(manX+5,manY+40, manX+10,manY+40, manX-1,manY+59, manX-5,manY+55);        //back shin
 fill(54,47,45);                 //shoe hair color
 quad(manX-5,manY+55, manX,manY+55, manX+5,manY+65, manX,manY+65);           //back shoe
 quad(manX+10,manY+55, manX+15,manY+55, manX+20,manY+65, manX+15,manY+65);           //front shoe
 rect(manX-5,manY-30, 25, 5);          //hair top bar
 rect(manX-5,manY-25, 15,5);           //hair mid
 rect(manX-5,manY-20, 10,5);           //hair low
   }else{
 noStroke();
 fill(233,203,141);              //skin color
 rect(manX-15,manY-30,25,30);           //head
 fill(0,191,243);                //dark shirt color
 rect(manX+20,manY+5,5,5);             //backforearm
 rect(manX+10,manY-5,15,10);           //back upper arm
 fill(109,207,246);              //light shirt color
 rect(manX-15,manY,5,20);              //front arm
 rect(manX-10,manY,25,25);             //bodmanY
 fill(233,203,141);              //skin color
 rect(manX-25,manY+10,10,10);          //back hand
 fill(109,207,246);              //light shirt color
 fill(233,203,141);              //skin color
 rect(manX+15,manY+10,10,10);          //front hand
 fill(246,150,121);              //light pants color
 rect(manX-10,manY+25,25,10);          //waist
 fill(242,108,79);               //dark pants color
 rect(manX-10,manY+25,10,5);
 rect(manX-10,manY+30,15,5);
 quad(manX-10,manY+32, manX-7,manY+35, manX-15,manY+40, manX-20,manY+40);          
 quad(manX-15,manY+40, manX-20,manY+40, manX-9,manY+59, manX-5,manY+55);   
 fill(246,150,121);              //light pants color
 quad(manX+5,manY+35, manX+10,manY+35, manX+2,manY+40, manX-5,manY+40);          
 quad(manX,manY+40, manX-5,manY+40, manX+6,manY+59, manX+10,manY+55);        
 fill(54,47,45);                 //shoe hair color
 quad(manX+10,manY+55, manX+5,manY+55, manX,manY+65, manX+5,manY+65);           //back shoe
 quad(manX-5,manY+55, manX-10,manY+55, manX-15,manY+65, manX-10,manY+65);         //front shoe
 rect(manX-15,manY-30, 25, 5);          //hair top bar
 rect(manX-5,manY-25, 15,5);           //hair mid
 rect(manX,manY-20, 10,5);           //hair low
}
}

void drawFrame8(){
if (manX < goldX) {
 noStroke();
 fill(233,203,141);              //skin color
 rect(manX-5,manY-35,25,30);           //head
 fill(0,191,243);               //dark shirt color
 quad(manX+10,manY-3, manX+18,manY-5, manX+25,manY, manX+25,manY+5);  //front arm
 fill(109,207,246);              //light shirt color
 rect(manX-25,manY-10,20,5);           //back arm upper
 quad(manX-25,manY-10, manX-20,manY-5, manX-25,manY+5, manX-30,manY); //backforearm
 rect(manX-10,manY-5,25,25);           //bodmanY
 fill(233,203,141);              //skin color
 rect(manX-35,manY,10,10);             //back hand
 fill(109,207,246);              //light shirt color
 fill(233,203,141);              //skin color
 rect(manX+20,manY,10,10);             //front hand
 fill(246,150,121);              //light pants color
 rect(manX-10,manY+20,25,10);          //waist
 quad(manX+15,manY+27, manX+12,manY+30, manX+20,manY+35, manX+25,manY+35); //front leg upper
 quad(manX+20,manY+35, manX+25,manY+35, manX+14,manY+54, manX+10,manY+50);     //front leg shin
 fill(242,108,79);               //dark pants color
 quad(manX-10,manY+30, manX-5,manY+30, manX-15,manY+40, manX-20,manY+40);  //back upper leg
 rect(manX-30,manY+35, 15,5);         //back shin
 fill(54,47,45);                 //shoe hair color
 rect(manX-35,manY+35,5,15);           //back shoe
 quad(manX+10,manY+50, manX+15,manY+50, manX+20,manY+60, manX+15,manY+60);   //front shoe
 rect(manX-5,manY-35, 25, 5);          //hair top bar
 rect(manX-5,manY-30, 10,5);           //hair mid
 rect(manX-5,manY-25, 5,5);            //hair low
   }else{
 noStroke();
 fill(233,203,141);              //skin color
 rect(manX-15,manY-35,25,30);           //head
 fill(0,191,243);               //dark shirt color
 quad(manX-10,manY-3, manX-18,manY-5, manX-25,manY, manX-25,manY+5);  //front arm
 fill(109,207,246);              //light shirt color
 rect(manX+10,manY-10,15,5);           //back arm upper
 quad(manX+25,manY-10, manX+20,manY-5, manX+25,manY+5, manX+30,manY); //backforearm
 rect(manX-10,manY-5,25,25);           //bodmanY
 fill(233,203,141);              //skin color
 rect(manX-35,manY,10,10);             //back hand
 fill(109,207,246);              //light shirt color
 fill(233,203,141);              //skin color
 rect(manX+20,manY,10,10);             //front hand
 fill(246,150,121);              //light pants color
 rect(manX-10,manY+20,25,10);          //waist
 quad(manX-10,manY+27, manX-7,manY+30, manX-15,manY+35, manX-20,manY+35); //front leg upper
 quad(manX-15,manY+35, manX-20,manY+35, manX-9,manY+54, manX-5,manY+50);  
 fill(242,108,79);               //dark pants color
 quad(manX+15,manY+30, manX+10,manY+30, manX+20,manY+40, manX+25,manY+40);  //back upper leg
 rect(manX+20,manY+35, 15,5);         //back shin
 fill(54,47,45);                 //shoe hair color
 rect(manX+35,manY+35,5,15);           //back shoe
 quad(manX-5,manY+50, manX-10,manY+50, manX-15,manY+60, manX-10,manY+60);   //front shoe
 rect(manX-15,manY-35, 25, 5);          //hair top bar
 rect(manX,manY-30, 10,5);           //hair mid
 rect(manX+5,manY-25, 5,5);            //hair low
   }
}


void monsterStuff(){
  if (eyeX<manX){
  strokeWeight(1);
  stroke(0);
  fill(255);
  ellipse(eyeX,eyeY, 40, 40);
  fill(255,0,0);
  ellipse(eyeX+15,eyeY,10,20);
  fill(0);
  ellipse(eyeX+17,eyeY,5,10);
  stroke(255,0,0);
  line(eyeX-19,eyeY,eyeX-9,eyeY-2);          //middle line
  line(eyeX-9,eyeY-2,eyeX,eyeY+2);            //middle line
  line(eyeX,eyeY+2,eyeX+9,eyeY-2);           //middle line
  line(eyeX-17,eyeY-9,eyeX-2,eyeY-16);       //top line
  line(eyeX-2,eyeY-16,eyeX+10,eyeY-10);         //top line
  line(eyeX-17,eyeY+8,eyeX-1,eyeY+13);       //bottom line
  line(eyeX-1,eyeY+13,eyeX+11,eyeY+11);       //bottom line
  }else{
  strokeWeight(1);
  stroke(0);
  fill(255);
  ellipse(eyeX,eyeY, 40, 40);
  fill(255,0,0);
  ellipse(eyeX-15,eyeY,10,20);
  fill(0);
  ellipse(eyeX-17,eyeY,5,10);
  stroke(255,0,0);
  line(eyeX+19,eyeY,eyeX+9,eyeY-2);          //middle line
  line(eyeX+9,eyeY-2,eyeX,eyeY+2);            //middle line
  line(eyeX,eyeY+2,eyeX-9,eyeY-2);           //middle line
  line(eyeX+17,eyeY-9,eyeX+2,eyeY-16);       //top line
  line(eyeX+2,eyeY-16,eyeX-10,eyeY-10);         //top line
  line(eyeX+17,eyeY+8,eyeX-1,eyeY+13);       //bottom line
  line(eyeX+1,eyeY+13,eyeX-11,eyeY+11);       //bottom line
  }  
  float dx = manX - eyeX;
  eyeX += dx * 0.035;
  float dy = manY - eyeY;
  eyeY += dy * 0.035;
}
void drawNight(){
  noStroke();          
  fill(0,dark);
  rect(0,0,width,height); 
}

void interactions(){
  if (abs(manX-goldX)<2 && abs(manY-goldY)<2){
    goldX = random(width/4, width*3/4);
    goldY = random(height/3.2, height*3/4);
    manX= 260;
    manY= 70;
    score = score + 50;
  }
  if (abs(eyeX-manX)<8 && abs(eyeY-manY)<8){
    score = score - 100;
    goldX = random(width/4, width*3/4);
    goldY = random(height/3.2, height*3/4);
    eyeX= 0;
    eyeY= random(height/4,height);
    manX= width;
    manY= random(height/4,height);
      
  }
}

void displayScore(){
  fill(0);
  rect(width-88,8,83,17);
  fill(255);
  textSize(12);
  text("Score",width-85,20);
  text(score, width-45, 20);
  if(score>=500){
    textSize(100);
    fill(255,215,0);
    text("YOU WIN", (width/2)-200, height/2);
    eyeKill = 1;
  }
}

void button(){
  fill(0);
  strokeWeight(4);
  stroke(255);
  rect(width-88, 33, 83,30);
  fill(255);
  textSize(12);
  text("Reset", width-65, 50);
}

void mousePressed() {
  if (mode == 0){
    if (mouseX >(width/2)-50 && mouseX<(width/2)+33 && mouseY>(height/2)+100 && mouseY<(height/2)+130){
      mode = 1;
    }
  }
  if (mode == 1){
    if (mouseX >width-88 && mouseX<width-5 && mouseY>33 && mouseY<63){
    reset();
    score= score -50;
    }
  }
}

void keyPressed() {
  if (key == 'q') {
    exit();                           
  }
}
