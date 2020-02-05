package edu.jsu.mcis;

import edu.jsu.mcis.TicTacToeModel.Result;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class TicTacToeController implements ActionListener {

    private final TicTacToeModel model;
    private final TicTacToeView view;
    
    /* CONSTRUCTOR */

    public TicTacToeController(int width) {
        
        /* Initialize model, view, and width */

        model = new TicTacToeModel(width);
        view = new TicTacToeView(this, width);
        
    }

    public String getMarkAsString(int row, int col) {
        return (model.getMark(row, col).toString());
    }

    public TicTacToeView getView() {
        return view;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String name = ((JButton) event.getSource()).getName();

        //ArrayList<String> indexes = new ArrayList(Arrays.asList(name.substring(6).split("-")));
        int row = Integer.parseInt(name.substring(6, 7));
        int col = Integer.parseInt(name.substring(7));


        model.makeMark(row, col);
        view.updateSquares();

        Result result = model.getResult();

        if (!(result == Result.NONE)) {
            view.showResult(result.toString());
            view.disableSquares();
        }


    }

}
