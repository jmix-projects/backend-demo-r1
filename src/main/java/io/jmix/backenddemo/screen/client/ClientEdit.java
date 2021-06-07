package io.jmix.backenddemo.screen.client;

import io.jmix.ui.screen.*;
import io.jmix.backenddemo.entity.Client;

@UiController("Client.edit")
@UiDescriptor("client-edit.xml")
@EditedEntityContainer("clientDc")
public class ClientEdit extends StandardEditor<Client> {
}