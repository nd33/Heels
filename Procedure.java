
public class Procedure extends ABSElement {
    private String name;
    private String argument;
    private Statements body;

    public static Procedure parse() {
        Procedure result = new Procedure();

        Parser.nextToken();
        if (!(Parser.getCurrentToken() instanceof IdentifierToken)) {
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "Expecting identifier after 'PROC'", Parser.getCurrentToken()));
            Parser.moveToNextStatementWithinProc();
            Statements.parse();
            return result;
        }
        result.setToken(Parser.getCurrentToken());
        result.setName(Parser.getCurrentToken().getName());
        Parser.addProcedure(result.name);
        Parser.nextToken();
        if (!(Parser.getCurrentToken() instanceof LBracketToken)) {
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "Expecting '(' identifier ')' after PROC ", Parser.getCurrentToken()));
            Parser.moveToNextStatementWithinProc();
            Statements.parse();
            return result;
        }
        Parser.nextToken();
        if (!(Parser.getCurrentToken() instanceof IdentifierToken)) {
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "Expecting '(' identifier ')' after PROC ", Parser.getCurrentToken()));
            Parser.moveToNextStatementWithinProc();
            Statements.parse();
            return result;
        }
        result.setArgument(Parser.getCurrentToken().getName());
        Parser.nextToken();
        if (!(Parser.getCurrentToken() instanceof RBracketToken)) {
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "Expecting '(' identifier ')' after PROC ", Parser.getCurrentToken()));
            Parser.moveToNextStatementWithinProc();
            Statements.parse();
            return result;
        }
        Parser.nextToken();
        result.setBody(Statements.parse());

        return result;
    }

    public String codeString() {
        if (argument != null) {
            Parser.setCurrProcArg(argument);
        } else {
            Parser.setCurrProcArg("");
        }
        String result = "/" + name;
        if (body != null) {
            result += body.codeString();
        }
        result += "} def\n";
        return result;
        //return "/" + getName() + " {\n" + getBody().codeString() + "} def\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public Statements getBody() {
        return body;
    }

    public void setBody(Statements body) {
        this.body = body;
    }
}
