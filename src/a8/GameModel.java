package a8;

import java.awt.Color;

public class GameModel {

	private JSpotBoard nextBoard;
	private boolean toggleTorusModeStatus = false;
	
	private int surviveThreshold = 2;
	private int revivalThreshold = 3;
	public JSpotBoard getNextBoard() {
		return nextBoard;
	}
	
	public void setTorusMode(){
		if (toggleTorusModeStatus == false) {
			toggleTorusModeStatus = true;
		} else {
			toggleTorusModeStatus = false;
		}
	}
	
	public void setSurviveThreshold(int survive) {
		surviveThreshold = survive;
	}
	public void setBirthThreshold(int birth) {
		revivalThreshold = birth;
	}
	
	public void calculateNextBoard(JSpotBoard board) {
		nextBoard = (new JSpotBoard(board.getSpotWidth(), board.getSpotHeight()));
		
	
		for (Spot s: nextBoard) {
			s.setBackground(Color.DARK_GRAY);
		}
		if (!(toggleTorusModeStatus)) {
			noTaurusAdvanceBoard(board);
		} else {
			TaurusModeAdvanceBoard(board);
		}
		
		
		
	}
	


	public void generateRandomBoard(JSpotBoard board) {
		nextBoard = (new JSpotBoard(board.getSpotWidth(), board.getSpotHeight()));
		for (Spot s: nextBoard) {
			double i = .5;
			if (i <= Math.random()) {
				s.setBackground(Color.RED);
				s.toggleSpot();
				s.setSpotColor(Color.RED);
			} else {
				s.setBackground(Color.DARK_GRAY);
				s.setSpotColor(Color.DARK_GRAY);
			}
		}
	}
	
	private void TaurusModeAdvanceBoard(JSpotBoard board) {
		for (Spot s: board) {
			//check for regeneration
			if (s.isEmpty()) {
				if (s.getSpotX() == 0) {
					if (s.getSpotY() == 0) {
						if (TTLSpotCheck(s) == revivalThreshold) {
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);		
						}
						continue;
					}
				}
				if (s.getSpotX() == s.getBoard().getSpotWidth() -1) {
					if (s.getSpotY() == 0) {
						if (TTRSpotCheck(s) == revivalThreshold) {
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
						}
						continue;
					}
				}
				if (s.getSpotX() == 0) {
					if (s.getSpotY() == s.getBoard().getSpotHeight() - 1) {
						if (TBLSpotCheck(s) == revivalThreshold) {
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
							
						}
						continue;
					}
				}
				if (s.getSpotX() == s.getBoard().getSpotWidth() -1) {
					if (s.getSpotY() == s.getBoard().getSpotHeight() - 1) {
						if (TBRSpotCheck(s) == revivalThreshold) {
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
							
						}
						continue;
					}
				}
				
				
				if (s.getSpotX() == 0) {
					// insert special case
					if (TLeftEdgeCheck(s) == revivalThreshold) {
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
						
					}
					continue;
					
				}
				
				if (s.getSpotY() == 0) {
					if (TTopEdgeCheck(s) == revivalThreshold) {
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
						
					}
					continue;
				}
				
				if (s.getSpotY() == board.getSpotHeight()-1) {
					if (TBottomEdgeCheck(s) == revivalThreshold) {
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
						
					}
					continue;
				}
				
				if (s.getSpotX() == board.getSpotWidth() -1) {
					if (TRightEdgeCheck(s) == revivalThreshold) {
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
						
					}
					continue;
				}
				
				
				if (regularSpotCheck(s) == revivalThreshold) {
					nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
					nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
					nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
					continue;
				}
			}
			// checks for survival
			if (!(s.isEmpty())) {
				if (s.getSpotX() == 0) {
					if (s.getSpotY() == 0) {
						if (TTLSpotCheck(s) == surviveThreshold || TTLSpotCheck(s) == revivalThreshold) {
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
						}
						continue;
					}
				}
				if (s.getSpotX() == s.getBoard().getSpotWidth() -1) {
					if (s.getSpotY() == 0) {
						if (TTRSpotCheck(s) == surviveThreshold|| TTRSpotCheck(s) == revivalThreshold) {
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
							
						}
						continue;
					}
				}
				if (s.getSpotX() == 0) {
					if (s.getSpotY() == s.getBoard().getSpotHeight() - 1) {
						if (TBLSpotCheck(s) == surviveThreshold|| TBLSpotCheck(s) == revivalThreshold) {
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
							
						}
						continue;
					}
				}
				if (s.getSpotX() == s.getBoard().getSpotWidth() -1) {
					if (s.getSpotY() == s.getBoard().getSpotHeight() - 1) {
						if (TBRSpotCheck(s) == surviveThreshold|| TBRSpotCheck(s) == revivalThreshold) {
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
							
						}
						continue;
					}
				}
				
				
				if (s.getSpotX() == 0) {
					// insert special case
					if (TLeftEdgeCheck(s) == surviveThreshold|| TLeftEdgeCheck(s) == revivalThreshold) {
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
						
					}
					continue;
				}
				
				if (s.getSpotY() == 0) {
					if (TTopEdgeCheck(s) == surviveThreshold || TTopEdgeCheck(s) == revivalThreshold) {
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
						
					}
					continue;
				}
				
				if (s.getSpotY() == board.getSpotHeight()-1) {
					if (TBottomEdgeCheck(s) == surviveThreshold|| TBottomEdgeCheck(s) == revivalThreshold) {
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
						
					}
					continue;
				}
				
				if (s.getSpotX() == board.getSpotWidth() -1) {
					if (TRightEdgeCheck(s) == surviveThreshold|| TRightEdgeCheck(s) == revivalThreshold) {
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
						
					}
					continue;
				}
				
				
				if (regularSpotCheck(s) == surviveThreshold|| regularSpotCheck(s) == revivalThreshold) {
					nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
					nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
					nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
					continue;
				}
			}
		
			
		}
		
	}
	
	private void noTaurusAdvanceBoard(JSpotBoard board) {
		for (Spot s: board) {
			//check for regeneration
			if (s.isEmpty()) {
				if (s.getSpotX() == 0) {
					if (s.getSpotY() == 0) {
						if (TLSpotCheck(s) == revivalThreshold) {
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);		
						}
						continue;
					}
				}
				if (s.getSpotX() == s.getBoard().getSpotWidth() -1) {
					if (s.getSpotY() == 0) {
						if (TRSpotCheck(s) == revivalThreshold) {
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
						}
						continue;
					}
				}
				if (s.getSpotX() == 0) {
					if (s.getSpotY() == s.getBoard().getSpotHeight() - 1) {
						if (BLSpotCheck(s) == revivalThreshold) {
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
							
						}
						continue;
					}
				}
				if (s.getSpotX() == s.getBoard().getSpotWidth() -1) {
					if (s.getSpotY() == s.getBoard().getSpotHeight() - 1) {
						if (BRSpotCheck(s) == revivalThreshold) {
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
							
						}
						continue;
					}
				}
				
				
				if (s.getSpotX() == 0) {
					// insert special case
					if (LeftEdgeCheck(s) == revivalThreshold) {
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
						
					}
					continue;
					
				}
				
				if (s.getSpotY() == 0) {
					if (TopEdgeCheck(s) == revivalThreshold) {
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
						
					}
					continue;
				}
				
				if (s.getSpotY() == board.getSpotHeight()-1) {
					if (BottomEdgeCheck(s) == revivalThreshold) {
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
						
					}
					continue;
				}
				
				if (s.getSpotX() == board.getSpotWidth() -1) {
					if (RightEdgeCheck(s) == revivalThreshold) {
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
						
					}
					continue;
				}
				
				
				if (regularSpotCheck(s) == revivalThreshold) {
					nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
					nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
					nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
					continue;
				}
			}
			// checks for survival
			if (!(s.isEmpty())) {
				if (s.getSpotX() == 0) {
					if (s.getSpotY() == 0) {
						if (TLSpotCheck(s) == surviveThreshold || TLSpotCheck(s) == revivalThreshold) {
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
						}
						continue;
					}
				}
				if (s.getSpotX() == s.getBoard().getSpotWidth() -1) {
					if (s.getSpotY() == 0) {
						if (TRSpotCheck(s) == surviveThreshold|| TRSpotCheck(s) == revivalThreshold) {
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
							
						}
						continue;
					}
				}
				if (s.getSpotX() == 0) {
					if (s.getSpotY() == s.getBoard().getSpotHeight() - 1) {
						if (BLSpotCheck(s) == surviveThreshold|| BLSpotCheck(s) == revivalThreshold) {
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
							
						}
						continue;
					}
				}
				if (s.getSpotX() == s.getBoard().getSpotWidth() -1) {
					if (s.getSpotY() == s.getBoard().getSpotHeight() - 1) {
						if (BRSpotCheck(s) == surviveThreshold|| BRSpotCheck(s) == revivalThreshold) {
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
							nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
							
						}
						continue;
					}
				}
				
				
				if (s.getSpotX() == 0) {
					// insert special case
					if (LeftEdgeCheck(s) == surviveThreshold|| LeftEdgeCheck(s) == revivalThreshold) {
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
						
					}
					continue;
				}
				
				if (s.getSpotY() == 0) {
					if (TopEdgeCheck(s) == surviveThreshold || TopEdgeCheck(s) == revivalThreshold) {
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
						
					}
					continue;
				}
				
				if (s.getSpotY() == board.getSpotHeight()-1) {
					if (BottomEdgeCheck(s) == surviveThreshold|| BottomEdgeCheck(s) == revivalThreshold) {
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
						
					}
					continue;
				}
				
				if (s.getSpotX() == board.getSpotWidth() -1) {
					if (RightEdgeCheck(s) == surviveThreshold|| RightEdgeCheck(s) == revivalThreshold) {
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
						nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
						
					}
					continue;
				}
				
				
				if (regularSpotCheck(s) == surviveThreshold|| regularSpotCheck(s) == revivalThreshold) {
					nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setBackground(Color.RED);
					nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).toggleSpot();
					nextBoard.getSpotAt(s.getSpotX(), s.getSpotY()).setSpotColor(Color.RED);
					continue;
				}
			}
		
			
		}
	}
	
	
	
	private int regularSpotCheck(Spot spot) {
		int neighbors = 0;
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		return neighbors;
	}
	private int TLSpotCheck(Spot spot) {
		int neighbors = 0;

		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		return neighbors;
	}
	private int TRSpotCheck (Spot spot) {
		int neighbors = 0;
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		return neighbors;
	}
	private int BLSpotCheck(Spot spot) {
		int neighbors = 0;
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		return neighbors;
	}
	private int BRSpotCheck(Spot spot) {
		int neighbors = 0;
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}

		return neighbors;
	}
	private int TopEdgeCheck (Spot spot) {
		int neighbors = 0;
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		return neighbors;
	}
	private int LeftEdgeCheck (Spot spot) {
		int neighbors = 0;

		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}

		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}

		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		return neighbors;
	}
	private int RightEdgeCheck (Spot spot) {
		int neighbors = 0;
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}

		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}

		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}

		return neighbors;
	}
	private int BottomEdgeCheck (Spot spot) {
		int neighbors = 0;
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}

		return neighbors;
	}
	// torus mode checkers
	
	private int TTLSpotCheck(Spot spot) {
		int neighbors = 0;

		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		
		// loop around
		if (!(spot.getBoard().getSpotAt(spot.getBoard().getSpotWidth() -1 , spot.getBoard().getSpotHeight()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getBoard().getSpotHeight()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getBoard().getSpotHeight()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getBoard().getSpotWidth() -1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getBoard().getSpotWidth() -1, spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		return neighbors;
	}
	private int TTRSpotCheck (Spot spot) {
		int neighbors = 0;
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		// loop around
		if (!(spot.getBoard().getSpotAt(0 , spot.getBoard().getSpotHeight()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(0, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(0, spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getBoard().getSpotHeight()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX() -1, spot.getBoard().getSpotHeight()-1).isEmpty())) {
			neighbors++;
		}
		return neighbors;
	}
	private int TBLSpotCheck(Spot spot) {
		int neighbors = 0;
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		//wrap
		if (!(spot.getBoard().getSpotAt(spot.getBoard().getSpotWidth() -1 , 0).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX() + 1, 0).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), 0).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getBoard().getSpotWidth() -1, spot.getSpotY() - 1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getBoard().getSpotWidth() -1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		return neighbors;
	}
	private int TBRSpotCheck(Spot spot) {
		int neighbors = 0;
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		//wrap
		if (!(spot.getBoard().getSpotAt(0 , 0).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(0 ,spot.getSpotY() ).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(0, spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), 0).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX() -1, 0).isEmpty())) {
			neighbors++;
		}

		return neighbors;
	}
	private int TTopEdgeCheck (Spot spot) {
		int neighbors = 0;
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		
		//wrap
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1,	spot.getBoard().getSpotHeight()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getBoard().getSpotHeight()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getBoard().getSpotHeight()-1).isEmpty())) {
			neighbors++;
		}
		
		
		return neighbors;
	}
	private int TLeftEdgeCheck (Spot spot) {
		int neighbors = 0;

		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}

		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}

		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		//wrap
		if (!(spot.getBoard().getSpotAt(spot.getBoard().getSpotWidth() -1, spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}

		if (!(spot.getBoard().getSpotAt(spot.getBoard().getSpotWidth() -1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getBoard().getSpotWidth() -1, spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		return neighbors;
	}
	private int TRightEdgeCheck (Spot spot) {
		int neighbors = 0;
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}

		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}

		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}
		//wrap
		if (!(spot.getBoard().getSpotAt(0, spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}

		if (!(spot.getBoard().getSpotAt(0, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(0, spot.getSpotY()+1).isEmpty())) {
			neighbors++;
		}

		return neighbors;
	}
	private int TBottomEdgeCheck (Spot spot) {
		int neighbors = 0;
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()-1).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, spot.getSpotY()).isEmpty())) {
			neighbors++;
		}
		// wrap
		
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()-1, 0).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX(), 0).isEmpty())) {
			neighbors++;
		}
		if (!(spot.getBoard().getSpotAt(spot.getSpotX()+1, 0).isEmpty())) {
			neighbors++;
		}

		return neighbors;
	}
}

