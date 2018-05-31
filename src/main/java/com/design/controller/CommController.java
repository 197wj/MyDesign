package com.design.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.design.service.comm.SerialTool;
import com.design.service.comm.serialException.NoSuchPort;
import com.design.service.comm.serialException.NotASerialPort;
import com.design.service.comm.serialException.PortInUse;
import com.design.service.comm.serialException.ReadDataFromSerialPortFailure;
import com.design.service.comm.serialException.SendDataToSerialPortFailure;
import com.design.service.comm.serialException.SerialPortInputStreamCloseFailure;
import com.design.service.comm.serialException.SerialPortOutputStreamCloseFailure;
import com.design.service.comm.serialException.SerialPortParameterFailure;

import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;

@Controller
public class CommController {

	@Autowired
	private SerialTool serialTool;

	@RequestMapping("/readPort.action")
	public void readPort(HttpServletRequest request)
			throws SerialPortParameterFailure, NotASerialPort, NoSuchPort, PortInUse, SendDataToSerialPortFailure,
			SerialPortOutputStreamCloseFailure, ReadDataFromSerialPortFailure, SerialPortInputStreamCloseFailure {

		SerialPort serialPort = serialTool.openPort("COM4", 9600);
		
		request.getSession().setAttribute("serialPort", serialPort);

		byte[] bs2 = null;
		
		while (true) {

			bs2 = serialTool.readFromPort(serialPort);
		}
	}
	
	@RequestMapping("/closePort.action")
	public String closePort(HttpServletRequest request) throws SerialPortParameterFailure, NotASerialPort, NoSuchPort, PortInUse, NoSuchPortException{
		
		SerialPort serialPort = (SerialPort) request.getAttribute("serialPort");
		
		serialTool.closePort(serialPort);
		
		return "index/welcome";
	}
	
}
