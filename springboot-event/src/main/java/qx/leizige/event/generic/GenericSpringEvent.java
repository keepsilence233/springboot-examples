package qx.leizige.event.generic;

import lombok.Getter;
import qx.leizige.event.BaseSpringEvent;


/**
 * @author leizige
 * 通用事件类型，我们现在可以灵活地发布任意事件，并且不再需要从 ApplicationEvent 扩展。
 * @param <T>
 */
@Getter
public class GenericSpringEvent<T> extends BaseSpringEvent<T> {

	private T what;

	protected boolean success;

	public GenericSpringEvent(T what, boolean success) {
		super(what);
		this.what = what;
		this.success = success;
	}


}
