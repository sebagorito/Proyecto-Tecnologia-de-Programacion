package visitors;

import platforms.*;

public interface VisitorEntityPlatform {
	public void visit (SolidBlock b);
	public void visit (LuckyBlock l);
	public void visit (SolidBrick b);
	public void visit(Flag flag);
	public void visit(CoinBlock c);
}
