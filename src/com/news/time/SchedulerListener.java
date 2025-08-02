package com.news.time;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.news.filtre.AnniversaireScheduler;



@WebListener
public class SchedulerListener implements ServletContextListener {

    private AnniversaireScheduler scheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("✅ Démarrage du scheduler des anniversaires...");
        scheduler = new AnniversaireScheduler();
        scheduler.demarrerPlanificateur(); // lance la tâche chaque jour
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("⛔ Arrêt du scheduler des anniversaires.");
        // Ici tu peux arrêter le scheduler si tu veux
    }
}
