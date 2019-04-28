/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notesElevesProfesseurs;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.util.ArrayList;

/**
 * Classe représentant un chart de statistiques de l'étudiant
 *
 * @author Célia
 */
class Stats_Bulletin extends ApplicationFrame
{
    private Stats_Bulletin(Eleve eleve, Ecole ecole) {
        super("Statistiques du Bulletin");

        Promotion promo = ecole.getPromo(eleve.getPromotion());

        ArrayList<Matiere> matieres = new ArrayList<>();
        for (Professeur prof : ecole.getProfesseur()) {
            try {
                eleve.getEvaluations(prof.getMatiere());
                matieres.add(prof.getMatiere());
            } catch (IllegalStateException ignored) {
            }
        }

        String chartTitle = "Etudiant : " + eleve.getNom().toUpperCase() + " " + eleve.getPrenom();
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                chartTitle,
                "Matiere",
                "Note",
                createDataset(eleve, promo, matieres),
                PlotOrientation.VERTICAL,
                true, true, false);

        ArrayList<String> Label_matieres = new ArrayList<>();
        matieres.forEach((m) -> {
            Label_matieres.add(m.toString());
        });

        SymbolAxis xAxis = new SymbolAxis("Matiere", Label_matieres.toArray(new String[matieres.size()]));

        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        final XYPlot plot = xylineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesPaint(2, Color.YELLOW);
        renderer.setSeriesPaint(3, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f));
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        renderer.setSeriesStroke(3, new BasicStroke(1.0f));

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRangeWithMargins(0, 20);

        plot.setDomainAxis(xAxis);
        plot.setRenderer(renderer);
        setContentPane(chartPanel);
    }

    private XYDataset createDataset(Eleve eleve, Promotion promo, ArrayList<Matiere> matieres) {

        final XYSeries promo_moy = new XYSeries("Promo");
        for (int i = 0; i < matieres.size(); i++) {
            promo_moy.add(i, promo.moyennePromotion(matieres.get(i)));
        }

        final XYSeries promo_mini = new XYSeries("Promo Mini");
        for (int i = 0; i < matieres.size(); i++) {
            promo_mini.add(i, promo.moyenneMaxMinPromotion(matieres.get(i), false));
        }

        final XYSeries etudiant = new XYSeries("Etudiant");
        for (int i = 0; i < matieres.size(); i++) {
            etudiant.add(i, eleve.getMoyenne(matieres.get(i)));
        }

        final XYSeries promo_maxi = new XYSeries("Promo Maxi");
        for (int i = 0; i < matieres.size(); i++) {
            promo_maxi.add(i, promo.moyenneMaxMinPromotion(matieres.get(i), true));
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(promo_mini);
        dataset.addSeries(etudiant);
        dataset.addSeries(promo_maxi);
        dataset.addSeries(promo_moy);
        return dataset;
    }

    /**
     * Créer un chart XY de représentation du bulletin
     *
     * @param eleve {@link Eleve} auquel appartien le bulletin
     * @param ecole {@link Ecole} d'où vient l'étudiant
     */
    public static void main(Eleve eleve, Ecole ecole) {
        Stats_Bulletin chart = new Stats_Bulletin(eleve, ecole);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

}
