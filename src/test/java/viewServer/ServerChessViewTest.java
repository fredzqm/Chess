package viewServer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import com.google.firebase.database.DatabaseReference;

import controller.ViewController;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;

public class ServerChessViewTest {
	DatabaseReference baseRef;
	DatabaseReference boardRef;
	DatabaseReference whiteBlackRef;
	DatabaseReference actionRef;
	ViewController viewController;

	private ServerChessView view; 
	
	@Captor
	ArgumentCaptor<BoardData> captor = ArgumentCaptor.forClass(BoardData.class);
	
	@Before
	public void setupMock() {
		this.baseRef = mock(DatabaseReference.class);
		this.boardRef = mock(DatabaseReference.class);
		this.whiteBlackRef = mock(DatabaseReference.class);
		this.actionRef = mock(DatabaseReference.class);
		when(this.baseRef.child("board")).thenReturn(this.boardRef);
		when(this.baseRef.child("whiteOrBlack")).thenReturn(this.whiteBlackRef);
		when(this.baseRef.child("action")).thenReturn(this.actionRef);
		
		this.viewController = mock(ViewController.class);
		
		this.view = ServerChessView.newInstance(this.baseRef, true);
		this.view.initializeViewController(this.viewController);
		
		reset(this.boardRef);
		reset(this.boardRef);
	}
	
	@Test
	public void testHighlightPiece11() {
		this.view.highLight(1, 1);
		this.view.repaint();
		
		verify(this.boardRef, times(1)).setValue(any());
		verify(this.boardRef).setValue(captor.capture());
		
		assertTrue(captor.getValue().pieces.get(0).get(0).isHightLight);
	}
	
	@Test
	public void testHighlightPiece27() {
		this.view.highLight(2, 7);
		this.view.repaint();
		
		verify(this.boardRef, times(1)).setValue(any());
		verify(this.boardRef).setValue(captor.capture());
		
		assertTrue(captor.getValue().pieces.get(1).get(6).isHightLight);
	}
	
	@Test
	public void testDehighlightAllPieces() {
		this.view.highLight(1, 1);
		this.view.highLight(2, 7);
		this.view.highLight(3, 2);
		this.view.deHighLightWholeBoard();
		this.view.repaint();
		
		verify(this.boardRef, times(1)).setValue(any());
		verify(this.boardRef).setValue(captor.capture());
		
		BoardData captured = captor.getValue();
		for(List<PieceData> list : captured.pieces) {
			for(PieceData p : list) {
				assertFalse(p.isHightLight);
			}
		}
	}
}
