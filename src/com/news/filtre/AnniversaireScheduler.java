package com.news.filtre;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.news.fonctions.Sms_Terrain;

public class AnniversaireScheduler {

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public void demarrerPlanificateur() {
        long delay = calculerDelaiAvantExecution(LocalTime.of(00, 00)); // Exécuter à 14h58

        scheduler.scheduleAtFixedRate(() -> {
            System.out.println(">> [ANNIVERSAIRE] Vérification à : " + LocalDateTime.now());

            try {
                Sms_Terrain sms = new Sms_Terrain();
                sms.envoyerAnniversaireClients();
                sms.envoyerAnniversaireClientD();
            } catch (Exception e) {
                System.err.println("Erreur lors de l'envoi des SMS : " + e.getMessage());
                e.printStackTrace();
            }

        }, delay, 24 * 60, TimeUnit.MINUTES); // Répéter toutes les 24h
    }

    private long calculerDelaiAvantExecution(LocalTime heureExecution) {
        LocalTime maintenant = LocalTime.now();
        long minutesMaintenant = maintenant.toSecondOfDay() / 60;
        long minutesCible = heureExecution.toSecondOfDay() / 60;

        if (minutesCible <= minutesMaintenant) {
            minutesCible += 24 * 60; // Exécution le lendemain
        }

        return minutesCible - minutesMaintenant;
    }
}
