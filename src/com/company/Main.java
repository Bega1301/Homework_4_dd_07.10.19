package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int[] heroesHealth = {250, 250, 250, 250}; // Doctor имеет здоровье =250

    public static int bossAttack = 50;
    public static int[] heroesAttack = {20, 20, 20, 0};// Doctor  не сражается, поэтому сила атаки ноль

    public static String bossDefenceType = "";
    public static int medicalTreatment = 10;

    public static String[] heroesAttactType = {"Physical", "Magical", "Kinetic", "Medical"};//4й игрок Doctor

    public static void main(String[] args) {
        // Игра с Боссом и 3 героями
        int roundNumber = 1;
        printStatistics(0);
        while (!isFinished()) {
            changeBossDefence();
            round(roundNumber);
            roundNumber++;
        }
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(3);
        bossDefenceType = heroesAttactType[randomIndex];
        System.out.println("Boss Defence:" + bossDefenceType);
    }

    public static void round(int number) {
        bossHit();
        heroesHit();
        medicalTreatment();
        printStatistics(number);
    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 && heroesHealth[3] <= 0) {
            System.out.println("Boss won!!");
            return true;
        }
        return false;
    }

    public static void bossHit() {
        for (int i = 0; i < heroesAttack.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                heroesHealth[i] = heroesHealth[i] - bossAttack;
            }
        }
    }

    public static void medicalTreatment() {
        for (int i = 0; i <= 2; i++) {
            if (heroesHealth[i] > 0) {
                heroesHealth[i] = heroesHealth[i] + medicalTreatment;
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesAttack.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType.equals(heroesAttactType[i])) {
                    Random r = new Random();
                    int randomNumber = r.nextInt(7) + 2;
                    bossHealth = bossHealth - heroesAttack[i] * randomNumber;
                    System.out.println("Critical damage:" + (heroesAttack[i] * randomNumber));
                }

            } else {
            }
            bossHealth = bossHealth - heroesAttack[i]; //bossHealth-=heroAttack[i];
        }
    }

    public static void printStatistics(int round) {
        System.out.println("_________________________");
        System.out.println("Round " + round);
        System.out.println("Boss Health:" + bossHealth);
        System.out.println("Warrior Health:" + heroesHealth[0]);
        System.out.println("Magical Health:" + heroesHealth[1]);
        System.out.println("Kinetic Health:" + heroesHealth[2]);
        System.out.println("Medical Health:" + heroesHealth[3]);

        System.out.println("_________________________");
    }
}
