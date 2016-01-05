package logic.main.com.boardgame;

import android.app.Activity;
import android.widget.ImageView;

public class BeadPlacer {
    ImageView imageView;

    public int placeBeads(int beadCount,String tag, int id,Board board,Activity mainActivity) {
        MoveController moveController = new MoveController();

        if (tag.equals("brownpiece") && beadCount != 0) {
            if (board.getMovingPlayer() == 1) {
                imageView = (ImageView) mainActivity.findViewById(id);
                imageView.setImageResource(R.drawable.brown1);
                imageView.setTag("brown1");
            }
            if (board.getMovingPlayer() == 2) {
                imageView = (ImageView) mainActivity.findViewById(id);
                imageView.setImageResource(R.drawable.brown2);
                imageView.setTag("brown2");
            }
            if (board.getMovingPlayer() == 3) {
                imageView = (ImageView) mainActivity.findViewById(id);
                imageView.setImageResource(R.drawable.brown3);
                imageView.setTag("brown3");
            }
            if (board.getMovingPlayer() == 4) {
                imageView = (ImageView) mainActivity.findViewById(id);
                imageView.setImageResource(R.drawable.brown4);
                imageView.setTag("brown4");
            }
            board = moveController.setMovingPlayer(board);
            beadCount--;
        }

        if (tag.equals("darkpiece") && beadCount != 0) {
            if (board.getMovingPlayer() == 1) {
                imageView = (ImageView) mainActivity.findViewById(id);
                imageView.setImageResource(R.drawable.dark1);
                imageView.setTag("dark1");
            }
            if (board.getMovingPlayer() == 2) {
                imageView = (ImageView) mainActivity.findViewById(id);
                imageView.setImageResource(R.drawable.dark2);
                imageView.setTag("dark2");
            }
            if (board.getMovingPlayer() == 3) {
                imageView = (ImageView) mainActivity.findViewById(id);
                imageView.setImageResource(R.drawable.dark3);
                imageView.setTag("dark3");
            }
            if (board.getMovingPlayer() == 4) {
                imageView = (ImageView) mainActivity.findViewById(id);
                imageView.setImageResource(R.drawable.dark4);
                imageView.setTag("dark4");
            }
            board = moveController.setMovingPlayer(board);
            beadCount--;
        }


return beadCount;

    }
}
