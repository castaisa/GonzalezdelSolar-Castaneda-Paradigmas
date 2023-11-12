package linea;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Linea {

    public static final String laFichaFueIngresadaFueraDelTablero = "La ficha fue ingresada fuera del tablero";
    public static final String lasDimensionesDebenSerPositivas = "Las dimensiones deben ser positivas";
    public static String varianteDeTriunfoNoValida = "Variante de triunfo no válida";
    public static String laColumnaEstaLlena = "La columna esta llena";
    private int width;
    private int height;
    private TriumphVariant triumphVariant;
    private GameState gameState;
    private ArrayList<ArrayList<String>> board;

    public Linea(int width, int height, Character triumphVariant) {
        if (width > 0 && height > 0){
                this.width = width;
                this.height = height;}
        else{
            throw new RuntimeException(lasDimensionesDebenSerPositivas);
        }
        List<TriumphVariant> triumphVariants = List.of(
                new WinsByRowsOrColumns(),
                new WinsByDiagonals(),
                new WinsByDiagonalsRowsOrColumns()
        );
        this.triumphVariant = triumphVariants.stream()
                .filter(variant -> variant.applies(triumphVariant))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(varianteDeTriunfoNoValida));
        this.gameState = new RedIsPlaying(this);
        this.board = new ArrayList<ArrayList<String>>();
        IntStream.range(0, width)
                .forEach(i -> board.add(new ArrayList<>()));
    }


    public boolean redsTurn() {
        return gameState.itsRedsTurn();
    }

    public boolean bluesTurn() {
        return gameState.itsBluesTurn();
    }

    public boolean finished() {
        return triumphVariant.somebodyWon(this) || boardIsFull();
    }

    public void playRedAt(int column) {
        gameState.redPlays(column);
    }

    public void playBlueAt(int column) {
        gameState.bluePlays(column);
    }

    public void addRedPieceAt(int column) {
        addPieceAt(column, "R");
    }

    public void addBluePieceAt(int column) {
        addPieceAt(column, "A");

    }

    public void switchGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public boolean boardIsFull() {
        return board.stream().allMatch((column) -> column.size() == height);
    }

    public boolean winByRow() {
        final int winningLength = 4;

        return IntStream.range(0, height)
                .anyMatch(startingRowIndex ->
                        IntStream.range(0, width - winningLength + 1)
                                .anyMatch(startingColumnIndex ->
                                        IntStream.range(0, winningLength)
                                                .allMatch(offset ->
                                                        isWithinBounds(startingColumnIndex + offset, startingRowIndex) &&
                                                                board.get(startingColumnIndex + offset).get(startingRowIndex).equals(board.get(startingColumnIndex).get(startingRowIndex))
                                                )
                                )
                );
    }

    public boolean winByColumn() {
        final int winningLength = 4;

        return board.stream().anyMatch(column ->
                IntStream.range(0, column.size() - winningLength + 1)
                        .anyMatch(startIndex ->
                                IntStream.range(0, winningLength)
                                        .allMatch(offset ->
                                                column.get(startIndex + offset).equals(column.get(startIndex))
                                        )
                        )
        );
    }

    public boolean winByDiagonal() {
        return IntStream.range(0, height)
                .anyMatch(rowIndex ->
                        IntStream.range(0, width)
                                .anyMatch(columnIndex -> winByAscendingDiagonal(rowIndex, columnIndex) || winByDescendingDiagonal(rowIndex, columnIndex))
                );
    }


    public String show() {
        StringBuilder display = new StringBuilder();

        display.append(" ");
        display.append(IntStream.rangeClosed(1, this.getWidth())
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" ")));
        display.append("\n");

        for (int i = this.getHeight() - 1; i >= 0; i--) {
            display.append("|");
            for (int j = 0; j < this.getWidth(); j++) {
                if (!this.board.get(j).isEmpty() && this.board.get(j).size() > i) {
                    String ficha = this.board.get(j).get(i);
                    String color = (ficha.equals("R")) ? "\u001B[31m" : ((ficha.equals("A")) ? "\u001B[34m" : "");
                    display.append(color).append(ficha).append("\u001B[0m");
                } else {
                    display.append(" ");
                }
                display.append("|");
            }
            display.append("\n");
        }

        display.append("-".repeat(this.getWidth() * 2 + 1));

        if (this.finished()) {
            display.append("\n");
            display.append("¡Juego Terminado! ");
            display.append(this.getGameResult());
        }

        return display.toString();
    }


    // Accessors
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public TriumphVariant getTriumphVariant() {
        return triumphVariant;
    }

    public char getTriumphsVariantChar() {
        return triumphVariant.getCharacter();
    }

    public String getGameResult() {
        return gameState.getGameResult();
    }


    private boolean winByAscendingDiagonal(int startingRowIndex, int startingColumnIndex) {
        final int winningLength = 4;

        return IntStream.range(0, winningLength)
                .allMatch(offset ->
                        isWithinBounds(startingRowIndex + offset, startingColumnIndex + offset) &&
                                isWithinBounds(startingRowIndex, startingColumnIndex) &&
                                board.get(startingRowIndex + offset).get(startingColumnIndex + offset).equals(board.get(startingRowIndex).get(startingColumnIndex))
                );
    }

    private boolean winByDescendingDiagonal(int startingRowIndex, int startingColumnIndex) {
        final int winningLength = 4;

        return IntStream.range(0, winningLength)
                .allMatch(offset ->
                        isWithinBounds(startingRowIndex + offset, startingColumnIndex - offset) &&
                                isWithinBounds(startingRowIndex, startingColumnIndex) &&
                                board.get(startingRowIndex + offset).get(startingColumnIndex - offset).equals(board.get(startingRowIndex).get(startingColumnIndex))
                );
    }

    private boolean isWithinBounds(int rowIndex, int columnIndex) {
        return rowIndex >= 0 && rowIndex < board.size() &&
                columnIndex >= 0 && columnIndex < board.get(rowIndex).size();
    }

    private void addPieceAt(int column, String piece) {
        if (column > width) {
            throw new RuntimeException(laFichaFueIngresadaFueraDelTablero);
        } else if (board.get(column - 1).size() == height) {
            throw new RuntimeException(laColumnaEstaLlena);
        }
        board.get(column - 1).add(piece);
    }
}
