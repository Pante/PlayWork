/*
 * Copyright (C) 2016 PanteLegacy @ karusmc.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.karusmc.commandworks;

import com.karusmc.commandwork.reference.CommandHandler;
import com.karusmc.commandworks.mock.MockSubcommand;

import org.junit.Test;

import static com.karusmc.commandwork.CommandManager.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class CommandManagerTest {
    
    CommandHandler handler = mock(CommandHandler.class);
    MockSubcommand command = new MockSubcommand();
    
    
    @Test
    public void register_putsCommandInRegisteredCommands() {
        register(command);
        
        boolean expected = REGISTERED_COMMANDS.containsKey(command.getName());
        
        assertTrue(expected);
    }
    
    
    @Test
    public void unregister_removesCommand() {
        REGISTERED_COMMANDS.put(command.getName(), command);
        unregister(command);
        
        boolean expected = !REGISTERED_COMMANDS.containsKey(command.getName()) && !REGISTERED_COMMANDS.containsValue(command);
        
        assertTrue(expected);
        
    }
    
    
    
    @Test
    public void register_callsHandlerRegister() {
        register(handler, command);
        
        boolean expected = REGISTERED_COMMANDS.containsKey(command.getName());
        
        verify(handler, times(1)).register(command);
        
        assertTrue(expected);
    }
    
    
    @Test
    public void unregister_callsHandlerUnregister() {
        REGISTERED_COMMANDS.put(command.getName(), command);
        unregister(handler, command);
        
        boolean expected = !REGISTERED_COMMANDS.containsKey(command.getName()) && !REGISTERED_COMMANDS.containsValue(command);
        
        verify(handler, times(1)).unregister(command);
        
        assertTrue(expected);
    }
    
}