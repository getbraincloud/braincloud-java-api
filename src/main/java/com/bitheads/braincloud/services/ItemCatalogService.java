package com.bitheads.braincloud.services;

import org.json.JSONException;
import org.json.JSONObject;

import com.bitheads.braincloud.client.BrainCloudClient;
import com.bitheads.braincloud.client.IServerCallback;
import com.bitheads.braincloud.client.ServiceName;
import com.bitheads.braincloud.client.ServiceOperation;
import com.bitheads.braincloud.comms.ServerCall;

public class ItemCatalogService {

    private enum Parameter {
        defId,
        context,
        pageOffset
    }

    private BrainCloudClient _client;

    public ItemCatalogService(BrainCloudClient client) {
        _client = client;
    }

    /**
	 * Reads an existing item definition from the server, with language fields
	 * limited to the current or default language
	 *
	 * Service Name - itemCatalog
	 * Service Operation - GET_CATALOG_ITEM_DEFINITION
	 *
	 * @param defId The unique id of the item definition.
     * @param callback  The callback handler
	 */
    public void getCatalogItemDefinition(String defId, IServerCallback callback) {
        try {
            JSONObject data = new JSONObject();
            data.put(Parameter.defId.name(), defId);

            ServerCall sc = new ServerCall(ServiceName.itemCatalog, ServiceOperation.GET_CATALOG_ITEM_DEFINITION, data, callback);
            _client.sendRequest(sc);
        } catch (JSONException ignored) {
        }
    }

	/**
	 * Retrieve page of catalog items from the server, with language fields limited to the 
	 * text for the current or default language.
	 *
	 * Service Name - itemCatalog
	 * Service Operation - GET_CATALOG_ITEMS_PAGE
	 *
	 * @param context   The json context for the page request.
     * @param callback  The callback handler
	 */
    public void getCatalogItemsPage(String context, IServerCallback callback) {
        try {
            JSONObject data = new JSONObject();
            data.put(Parameter.context.name(), new JSONObject(context));

            ServerCall sc = new ServerCall(ServiceName.itemCatalog, ServiceOperation.GET_CATALOG_ITEMS_PAGE, data, callback);
            _client.sendRequest(sc);
        } catch (JSONException ignored) {
        }
    }

	/**
	 * Gets the page of catalog items from the server based ont he encoded 
	 * context and specified page offset, with language fields limited to the 
	 * text fir the current or default language
	 *
	 * Service Name - itemCatalog
	 * Service Operation - GET_CATALOG_ITEMS_PAGE_OFFSET
	 *
	 * @param context       The context string returned from the server from a 
     *                      previous call to GetCatalogItemsPage or GetCatalogItemsPageOffset.
	 * @param pageOffset    The positive or negative page offset to fetch. 
     *                      Uses the last page retrieved using the context string to determine a starting point.
     * @param callback      The callback handler
	 */
    public void getCatalogItemsPageOffset(String context, int pageOffset, IServerCallback callback) {
        try {
            JSONObject data = new JSONObject();
            data.put(Parameter.context.name(), context);
            data.put(Parameter.pageOffset.name(), pageOffset);

            ServerCall sc = new ServerCall(ServiceName.itemCatalog, ServiceOperation.GET_CATALOG_ITEMS_PAGE_OFFSET, data, callback);
            _client.sendRequest(sc);
        } catch (JSONException ignored) {
        }
    }
}
