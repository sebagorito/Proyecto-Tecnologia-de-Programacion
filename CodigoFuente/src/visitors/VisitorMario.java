package visitors;
import enemies.*;
import pickUps.*;
public interface VisitorMario {
	void visit(FireFlower f);
	void visit(GreenMush g);
	void visit(SuperMush s);
	void visit(Star s);
	void visit(Coin c);
	void visit(Lakitu l);
	void visit(Goomba goomba);
	void visit(PiranhaPlant p);
	void visit(BuzzyBeetle b);
	void visit(KoopaTroopa k);
	void visit(Spiny spiny);
}
