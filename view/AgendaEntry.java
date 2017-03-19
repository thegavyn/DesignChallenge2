package view;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.Agenda;
import model.AgendaUtility;

/**
 * Created by Mina on 3/12/2017.
 */
public class AgendaEntry extends HBox {

    private CheckBox checkBox;
    private Text textEntry;
    private Button deleteButton;
    private Agenda agenda;

    public AgendaEntry(Agenda agenda) {
        this.agenda = agenda;

        checkBox = new CheckBox();
        textEntry = new Text();
        textEntry.setText(agenda.getStartDate().toLocalTime()
                + " - " + agenda.getEndDate().toLocalTime()
                + agenda.getName() + "\n");
        textEntry.setFill(AgendaUtility.convertColor(agenda.getColor()));
        textEntry.setFont(Font.font("Arial", 16));
        deleteButton = new Button("x");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (deleteButton.getParent() != null) {
//                    deleteButton.getParent().getChildren().remove(deleteButton);
                    ((TextFlow)deleteButton.getParent().getParent()).getChildren().remove(this);
                }
            }
        });

        this.getChildren().add(checkBox);
        this.getChildren().add(new Pane());
        this.getChildren().add(textEntry);
        this.getChildren().add(new Pane());
        this.getChildren().add(deleteButton);
        this.getChildren().add(new Text("\n"));
    }

}
