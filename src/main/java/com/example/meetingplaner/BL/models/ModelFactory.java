package com.example.meetingplaner.BL.models;

public class ModelFactory {

    private DataModel dataModel;

    public ModelFactory() {}

    public DataModel getDataModel(){
        if(dataModel == null){
            dataModel = new DataModelManager();
        }
        return dataModel;
    }
}
