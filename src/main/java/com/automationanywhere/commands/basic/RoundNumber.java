package com.automationanywhere.commands.basic;


import com.automationanywhere.botcommand.data.impl.NumberValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

//BotCommand makes a class eligible for being considered as an action.
@BotCommand

//CommandPks adds required information to be displayable on GUI.
@CommandPkg(
        //Unique name inside a package and label to display.
        name = "Round Number", label = "Round Number",
        node_label = "Round Number", description = "Round Number to x decimals", icon = "pkg.svg",

        //Return type information. return_type ensures only the right kind of variable is provided on the UI.
        return_label = "Rounded Number", return_type = DataType.NUMBER, return_required = true)

public class RoundNumber {

    //Messages read from full qualified property file name and provide i18n capability.
    private static final Messages MESSAGES = MessagesFactory.getMessages("com.automationanywhere.botcommand.demo.messages");

    //Identify the entry point for the action. Returns a Value<String> because the return type is String.
    @Execute
    public NumberValue action(

            @Idx(index = "1", type = AttributeType.NUMBER)
            @Pkg(label = "Number to round", default_value_type = DataType.NUMBER)
            @NotEmpty Double Number,

            @Idx(index = "2", type = AttributeType.NUMBER)
            @Pkg(label = "Number of Decimals", default_value_type = DataType.NUMBER)
            @NotEmpty Double Decimals

            ) {


        BigDecimal bd = new BigDecimal(Number).setScale(Decimals.intValue(), RoundingMode.HALF_EVEN);

        //Return NumberValue.
        return new NumberValue(bd.doubleValue());
    }

}
