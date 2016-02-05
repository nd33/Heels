/**
 * Created by jm360 on 05/02/16.
 */
public class PrimaryExpression extends Expression {
    int value;
    boolean isNumber;
    String name;

    public static PrimaryExpression parse () {
        PrimaryExpression result = new PrimaryExpression();
        if (Parser.currentToken instanceof NumberToken) {
            result.value = Parser.currentToken.getValue();
            result.isNumber = true;
        } else if (Parser.currentToken instanceof IdentifierToken) {
            result.name = Parser.currentToken.getName();
            result.isNumber = false;
        } else {
            //Add Errors
        }
        Parser.nextToken();
        return result;
    }
}