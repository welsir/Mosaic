export function list2Map<T> (list:T[],idName:string){
    try{
        const map = new Map<string, T>();
        for (let item of list) {
            map.set(item[idName], item);
        }
        return map
    } catch (e){
        console.error(e)
        return new Map()
    }
}

export function deepCopy(data:any){
    try{
        return JSON.parse(JSON.stringify(data))
    } catch (e){
        console.error(e)
        return null
    }
}
