package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, rightPressed, leftPressed, enterPressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (gp.gameState == gp.titleState) {
            titleState(code);
        } else if (gp.gameState == gp.playState) {
            playState(code);
        } else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        } else if (gp.gameState == gp.dialogueState) {
            dialogueState(code);
        } else if (gp.gameState == gp.characterState) {
            characterState(code);
        }

    }

    public void titleState(int code) {
        if (gp.ui.titleScreenState == 0) {

            if (code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2)
                    gp.ui.commandNum = 0;
            }
            if (code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0)
                    gp.ui.commandNum = 2;
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.ui.titleScreenState = 1;
                }
                if (gp.ui.commandNum == 1) {

                }
                if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        } else if (gp.ui.titleScreenState == 1) {

            if (code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 3)
                    gp.ui.commandNum = 0;
            }
            if (code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0)
                    gp.ui.commandNum = 3;
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                }
                if (gp.ui.commandNum == 1) {
                    gp.gameState = gp.playState;

                }
                if (gp.ui.commandNum == 2) {
                    gp.gameState = gp.playState;

                }
                if (gp.ui.commandNum != 3) {
                    gp.playMusic(0);
                }
                if (gp.ui.commandNum == 3) {
                    gp.ui.commandNum = 0;
                    gp.ui.titleScreenState = 0;
                }
            }
        }
    }

    public void playState(int code) {

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
    }

    public void dialogueState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }

    public void characterState(int code) {
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_W) {
            gp.ui.slotRow--;
            gp.playSE(9);
            if(gp.ui.slotRow == -1)
                gp.ui.slotRow = 3;
        }
        if (code == KeyEvent.VK_D) {
            gp.ui.slotCol++;
            gp.playSE(9);
            if (gp.ui.slotCol == 5)
                gp.ui.slotCol = 0;
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.slotRow++;
            gp.playSE(9);
            if (gp.ui.slotRow == 4)
                gp.ui.slotRow = 0;
        }
        if (code == KeyEvent.VK_A) {
            gp.ui.slotCol--;
            gp.playSE(9);
            if (gp.ui.slotCol == -1)
                gp.ui.slotCol = 4;
        }
        if (code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
