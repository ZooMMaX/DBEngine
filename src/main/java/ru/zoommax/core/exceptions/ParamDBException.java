package ru.zoommax.core.exceptions;

import ru.zoommax.api.DBType;

public class ParamDBException extends Exception {
    public ParamDBException(DBType type, String params) {
        super("For DB "+type.name()+" need more params. Params list: "+params);
    }
}
