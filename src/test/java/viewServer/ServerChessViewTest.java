package viewServer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import controller.ViewController;
import viewServer.ActionData.ClickData;

public class ServerChessViewTest {
	DatabaseReference baseRef;
	DatabaseReference boardRef;
	DatabaseReference whiteBlackRef;
	DatabaseReference actionRef;
	DatabaseReference clickRef;
	DatabaseReference requestRef;
	DatabaseReference askForDrawRef;
	ViewController viewController;

	private ServerChessView view; 
	
	@Captor
	ArgumentCaptor<BoardData> boardCaptor = ArgumentCaptor.forClass(BoardData.class);
	
	@Captor
	ArgumentCaptor<ValueEventListener> listenerCaptor = ArgumentCaptor.forClass(ValueEventListener.class);
	
	@Before
	public void setupMock() {
		this.baseRef = mock(DatabaseReference.class);
		this.boardRef = mock(DatabaseReference.class);
		this.whiteBlackRef = mock(DatabaseReference.class);
		this.actionRef = mock(DatabaseReference.class);
		this.clickRef = mock(DatabaseReference.class);
		this.requestRef = mock(DatabaseReference.class);
		this.askForDrawRef = mock(DatabaseReference.class);
		when(this.baseRef.child("board")).thenReturn(this.boardRef);
		when(this.baseRef.child("whiteOrBlack")).thenReturn(this.whiteBlackRef);
		when(this.baseRef.child("action")).thenReturn(this.actionRef);
		when(this.actionRef.child("click")).thenReturn(this.clickRef);
		when(this.baseRef.child("request")).thenReturn(this.requestRef);
		when(this.requestRef.child("askForDraw")).thenReturn(this.askForDrawRef);
		
		this.viewController = mock(ViewController.class);
		
		this.view = ServerChessView.newInstance(this.baseRef, true);
		this.view.initializeViewController(this.viewController);
		
		verify(this.actionRef).addValueEventListener(listenerCaptor.capture());
		
		reset(this.boardRef);
		reset(this.boardRef);
	}
	
	@Test
	public void testHighlightPiece11() {
		this.view.highLight(1, 1);
		this.view.repaint();
		
		verify(this.boardRef, times(1)).setValue(any());
		verify(this.boardRef).setValue(boardCaptor.capture());
		
		assertTrue(boardCaptor.getValue().pieces.get(7).get(0).isHightLight);
	}
	
	@Test
	public void testHighlightPiece27() {
		this.view.highLight(2, 7);
		this.view.repaint();
		
		verify(this.boardRef, times(1)).setValue(any());
		verify(this.boardRef).setValue(boardCaptor.capture());
		
		assertTrue(boardCaptor.getValue().pieces.get(1).get(1).isHightLight);
	}
	
	@Test
	public void testDehighlightAllPieces() {
		this.view.highLight(1, 1);
		this.view.highLight(2, 7);
		this.view.highLight(3, 2);
		this.view.deHighLightWholeBoard();
		this.view.repaint();
		
		verify(this.boardRef, times(1)).setValue(any());
		verify(this.boardRef).setValue(boardCaptor.capture());
		
		BoardData captured = boardCaptor.getValue();
		for(List<PieceData> list : captured.pieces) {
			for(PieceData p : list) {
				assertFalse(p.isHightLight);
			}
		}
	}
	
	@Test
	public void testClick() throws InterruptedException {
		ValueEventListener listener = listenerCaptor.getValue();
		DataSnapshot data = mock(DataSnapshot.class);
		ActionData action = new ActionData();
		action.click = new ClickData();
		action.click.i = 1;
		action.click.j = 1;
		
		when(data.getValue(ActionData.class)).thenReturn(action);
		
		listener.onDataChange(data);
		
		Thread.sleep(10);
		
		verify(this.viewController).click(2, 7, true);
		verify(this.clickRef).removeValue();
	}
	
}
