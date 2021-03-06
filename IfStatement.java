
public class IfStatement extends Statement {
    private Statements then;
    private Statements or;
    private Expression condition;

    public static IfStatement parse() {
        IfStatement result = new IfStatement();
        Parser.nextToken();
        result.setCondition(Expression.parse());

        if (!(result.getCondition() instanceof BinaryExpression) || !((BinaryExpression) result.getCondition()).isOperatorBoolean() || result.getCondition().numberOfBooleanOperators() != 1) {
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "IF condition must be a boolean expression", result.condition.getToken()));
        }

        if (!(Parser.getCurrentToken() instanceof ThenToken)) {
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "Expecting 'THEN'", "Inserting 'THEN'", Parser.getCurrentToken()));
            if (!Parser.moveToNextWithinIf(new ThenToken(0))) {
                Parser.nextToken();
                return result;
            }
        }
        Parser.nextToken();
        result.setThen(Statements.parse());
        if (!(Parser.getCurrentToken() instanceof ElseToken)) {
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "Expecting 'ELSE' after THEN [Statements]", "Inserting ELSE", Parser.getCurrentToken()));
            if (!Parser.moveToNextWithinIf(new ElseToken(0))) {
                Parser.nextToken();
                return result;
            }
        }
        Parser.nextToken();
        result.setOr(Statements.parse());
        if (!(Parser.getCurrentToken() instanceof EndIfToken)) {
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "Expecting 'ENDIF'", "Inserting 'ENDIF'", Parser.getCurrentToken()));
            if (!Parser.moveToNextWithinIf(new EndIfToken(0))) {
                Parser.nextToken();
                return result;
            }
        }
        Parser.nextToken();
        return result;
    }

    public String codeString() {
        String result = "";
        if (condition != null) {
            result += condition.codeString();
        }
        result += "{\n";
        if (then != null) {
            result += then.codeString();
        }
        result += "} {\n";
        if (or != null) {
            result += or.codeString();
        }
        result += "} ifelse \n";
        return result;
        //return getCondition().codeString() + "{\n" + getThen().codeString() + "} {\n" + getOr().codeString() + "} ifelse \n";
    }

    public Statements getThen() {
        return then;
    }

    public void setThen(Statements then) {
        this.then = then;
    }

    public Statements getOr() {
        return or;
    }

    public void setOr(Statements or) {
        this.or = or;
    }

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }
}
