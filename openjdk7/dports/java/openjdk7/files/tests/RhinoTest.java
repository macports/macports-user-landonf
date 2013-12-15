import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Set;

public class RhinoTest extends Base {
    public static void main (String[] args) {
        new RhinoTest().dispatch(args);
    }

    protected void run (Set<Variant> variants) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        if (engine == null)
            throw fail("Missing JavaScript engine");

        Object result;
        try {
            result = engine.eval("1+1");
        } catch (ScriptException e) {
            throw fail("Failed to evaluate JS: " + e);
        }

        if (!result.equals(new Double(2.0)))
            throw fail("Unexpected result: " + result);

        pass("Computed result: " + result);
    }
}
