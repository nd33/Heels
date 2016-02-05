/**
 * Created by jm360 on 05/02/16.
 */
public class Statment {
    public static Statment parse () {
        Statment result = new Statment();
        if (Parser.currentToken instanceof MoveToken) {
            result = MoveStatment.parse();
        } else if (Parser.currentToken instanceof IfToken) {
            result = IfStatment.parse();
        } else if (Parser.currentToken instanceof IdentifierToken) {
            result = ProcCallStatment.parse();
        } else {
            //Add Error
        }
        return result;
    }

    public String codeString () {
        return "";
    }
}
